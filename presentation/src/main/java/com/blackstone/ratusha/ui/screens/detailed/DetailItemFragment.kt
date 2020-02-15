package com.blackstone.ratusha.ui.screens.detailed

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.FragmentFullInfoBinding
import com.blackstone.ratusha.ui.adapter.recipe.RecipeAdapter
import com.blackstone.ratusha.ui.base.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.ui.screens.detailDialog.DetailedDialog
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class DetailItemFragment : BaseMvvmFragment<DetailItemViewModel, ControllerRouter, FragmentFullInfoBinding>(){

    private val adapter = RecipeAdapter()

    override fun provideViewModel(): DetailItemViewModel {
        return ViewModelProviders.of(this).get(DetailItemViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_full_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setKeyboardVisibilityEvent()

        this.arguments?.let {
            viewModel.getItemAndRecipe(it.getInt(ID_ITEM, 0))
        }

        binding.recyclerInfo.setHasFixedSize(true)
        binding.recyclerInfo.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerInfo.isNestedScrollingEnabled = false
        binding.recyclerInfo.adapter = adapter

        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.apply {
            listAdapter.observe(viewLifecycleOwner, Observer { list ->
                adapter.setItems(list)
            })
        }

        adapter.onClickItemSubject().observe(viewLifecycleOwner, Observer { clickItem ->
            activity?.let { activity ->
                DetailedDialog().show(activity.supportFragmentManager, DetailedDialog.DETAILED_DIALOG)
            }

        })
    }

    private fun setKeyboardVisibilityEvent() {
        KeyboardVisibilityEvent.setEventListener(activity) { isOpen ->
            measureContentView()
            val height = binding.headLayout.measuredHeight
            if (isOpen) {
                animate(height, 1)
            } else {
                setContentHeight(height)
            }
        }
    }

    private fun measureContentView() {
        val widthMS = View.MeasureSpec.makeMeasureSpec(binding.headLayout.width, View.MeasureSpec.AT_MOST)
        val heightMS = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        binding.headLayout.measure(widthMS, heightMS)
    }

    private fun animate(from: Int, to: Int) {
        val valuesHolder: PropertyValuesHolder = PropertyValuesHolder.ofInt("prod", from, to)

        val animator = ValueAnimator.ofPropertyValuesHolder(valuesHolder)
        animator.duration = 300
        animator.addUpdateListener {
            val value = animator.getAnimatedValue("prop") as Int? ?: 1
            binding.headLayout.layoutParams.height = value
            binding.headLayout.requestLayout()
            binding.headLayout.invalidate()
        }
        animator.start()
    }

    private fun setContentHeight(height: Int) {
        binding.headLayout.layoutParams.height = height
        binding.headLayout.requestLayout()
    }


    // Get Instance

    companion object {
        const val TAG = "DetailItemFragment"

        private const val ID_ITEM = "ID_ITEM"

        fun getInstance(id: Int): Fragment {
            val fragment = DetailItemFragment()
            val bundle = Bundle()
            bundle.putInt(ID_ITEM, id)
            fragment.arguments = bundle

            return fragment
        }
    }
}
