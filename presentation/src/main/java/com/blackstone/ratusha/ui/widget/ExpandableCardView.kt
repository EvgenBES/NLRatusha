package com.blackstone.ratusha.ui.widget

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.IdRes
import android.support.annotation.IntegerRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.blackstone.ratusha.R
import com.blackstone.ratusha.utils.DisplayUtils.getDensityDouble
import de.hdodenhof.circleimageview.CircleImageView

/**
 * @author Evgeny Butov
 * @created 23.03.2019
 */
class ExpandableCardView : FrameLayout {

    private val ivItem by bind<CircleImageView>(R.id.iv_card_expand)
    private val tvTitle by bind<TextView>(R.id.tv_card_title)
    private val tvQuantity by bind<TextView>(R.id.tv_card_quantity)
    private val tvRemainder by bind<TextView>(R.id.tv_card_remainder)
    private val tvPrice by bind<TextView>(R.id.tv_card_price)
    private val tvTotal by bind<TextView>(R.id.tv_card_total)
    private val tvTotalRemain by bind<TextView>(R.id.tv_card_total_remain)
    private val llArrow by bind<LinearLayout>(R.id.bnt_arrow_down)
    private val ivArrow by bind<ImageView>(R.id.imgArrow)
    private val llProgress by bind<LinearLayout>(R.id.ll_progress)
    private val layoutContent by bind<View>(R.id.layout_content)
    private val wrapProgress by bind<ConstraintLayout>(R.id.wrap_progress)

    constructor(context: Context, type: Int) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        inflate(context, R.layout.view_expandable_card, this)

        var expanded = false
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableCardView)
            type = typedArray.getInt(R.styleable.ExpandableCardView_type, 0)
            cardImage = typedArray.getResourceId(R.styleable.ExpandableCardView_image, 0)
            cardTitle = typedArray.getString(R.styleable.ExpandableCardView_title) ?: "Неизвестный предмет"
            cardQuantity = typedArray.getString(R.styleable.ExpandableCardView_quantity) ?: "0/0"
            cardRemainder = typedArray.getString(R.styleable.ExpandableCardView_remainder) ?: ""
            cardPrice = typedArray.getString(R.styleable.ExpandableCardView_price) ?: "0"
            cardTotal = typedArray.getString(R.styleable.ExpandableCardView_total) ?: "0 / 0"
            cardTotalRemain = typedArray.getString(R.styleable.ExpandableCardView_total_remain) ?: ""
            cardProgress = typedArray.getInt(R.styleable.ExpandableCardView_progress, 0)
            expanded = typedArray.getBoolean(R.styleable.ExpandableCardView_expanded, false)
            expandDuration = typedArray.getInt(R.styleable.ExpandableCardView_expand_duration, 200).toLong()

            typedArray.recycle()
        }

        post {
            if (expanded) {
                expand(false)
            } else {
                setHeightToZero(false)
            }
        }

        llArrow.setOnClickListener {
            if (isExpanded) {
                collapse(true)
            } else {
                expand(true)
            }
        }

        setCardViewColor(type)
    }

    private fun <T : View> View.bind(@IdRes res: Int): Lazy<T> =
        lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }

    private fun rotateArrow(rotation: Float, animate: Boolean) {
        ViewCompat.animate(llArrow)
            .rotation(rotation)
            .withLayer()
            .setDuration(if (animate) expandDuration else 0)
            .start()
    }

    private fun setHeightToZero(animate: Boolean) {
        if (animate) {
            animate(layoutContent.height, 0)
        } else {
            setContentHeight(0)
        }
    }

    private fun setHeightToContentHeight(animate: Boolean) {
        measureContentView()
        val targetHeight = layoutContent.measuredHeight
        if (animate) {
            animate(0, targetHeight)
        } else {
            setContentHeight(targetHeight)
        }
    }

    private fun setContentHeight(height: Int) {
        layoutContent.layoutParams.height = height
        layoutContent.requestLayout()
    }

    private fun measureContentView() {
        val widthMS = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST)
        val heightMS = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        layoutContent.measure(widthMS, heightMS)
    }

    private fun animate(from: Int, to: Int) {
        val valuesHolder: PropertyValuesHolder = PropertyValuesHolder.ofInt("prop", from, to)

        val animator = ValueAnimator.ofPropertyValuesHolder(valuesHolder)
        animator.duration = expandDuration
        animator.addUpdateListener {
            val value = animator.getAnimatedValue("prop") as Int? ?: 0
            layoutContent.layoutParams.height = value
            layoutContent.requestLayout()
            invalidate()
        }
        animator.start()
    }

    /**
     * Check if the card is expanded
     */
    var isExpanded = false
        private set

    /**
     * Expand the Card
     */
    private fun expand(animate: Boolean) {
        if (isExpanded) return

        setHeightToContentHeight(animate)
        rotateArrow(180f, animate)
        isExpanded = true
    }

    /**
     * Collapse the Card
     */
    private fun collapse(animate: Boolean) {
        if (!isExpanded) return

        setHeightToZero(animate)
        rotateArrow(0f, animate)
        isExpanded = false
    }


    /**
     * @property cardImage The title of the card
     */
    open var cardImage: Int? = null
        set(image) {
            if (image != null)
                ivItem.setImageResource(image)
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as title
     * @see cardImage
     */
    open fun setCardImage(@IntegerRes resId: Int) {
        cardImage = context.resources.getInteger(resId)
    }

    /**
     * @property cardTitle The title of the card
     */
    open var cardTitle: CharSequence
        get() = tvTitle.text
        set(title) {
            tvTitle.text = title
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as title
     * @see cardTitle
     */
    open fun setCardTitle(@StringRes resId: Int) {
        cardTitle = context.getString(resId)
    }



    /**
     * @property cardQuantity The description of the card
     */
    open var cardQuantity: CharSequence
        get() = tvQuantity.text
        set(quantity) {
            tvQuantity.text = quantity
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as description
     * @see cardQuantity
     */
    open fun setCardQuantity(@StringRes resId: Int) {
        cardQuantity = context.getString(resId)
    }

    /**
     * @property cardRemainder The description of the card
     */
    open var cardRemainder: CharSequence
        get() = tvRemainder.text
        set(remainder) {
            tvRemainder.text = remainder
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as description
     * @see cardRemainder
     */
    open fun setCardRemainder(@StringRes resId: Int) {
        cardRemainder = context.getString(resId)
    }


    /**
     * @property cardPrice The description of the card
     */
    open var cardPrice: CharSequence
        get() = tvPrice.text
        set(price) {
            tvPrice.text = price
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as description
     * @see cardPrice
     */
    open fun setCardPrice(@StringRes resId: Int) {
        cardPrice = context.getString(resId)
    }


    /**
     * @property cardTotal The description of the card
     */
    open var cardTotal: CharSequence
        get() = tvTotal.text
        set(total) {
            tvTotal.text = total
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as description
     * @see cardTotal
     */
    open fun setCardTotal(@StringRes resId: Int) {
        cardTotal = context.getString(resId)
    }


    /**
     * @property cardTotalRemain The description of the card
     */
    open var cardTotalRemain: CharSequence
        get() = tvTotalRemain.text
        set(totalRemain) {
            tvTotalRemain.text = totalRemain
        }

    /**
     * Sets the title of the card
     * @param resId String resource to display as description
     * @see cardTotalRemain
     */
    open fun setCardTotalRemain(@StringRes resId: Int) {
        cardTotalRemain = context.getString(resId)
    }

    /**
     * @property expandDuration The duration of the expand animation
     * @throws IllegalArgumentException if the duration is <= 0
     */
    open var expandDuration: Long = 200
        set(duration) {
            if (duration > 0) {
                field = duration
            } else {
                throw IllegalArgumentException("Card Expand Duration can not be smaller than or equal to 0")
            }
        }


    /**
     * @property cardProgress
     */
    open var cardProgress: Int = 0
        set(progress) {
            setProgressWidth((progress * 1.14 * getDensityDouble(context)).toInt())
            field = progress
        }

    private fun setProgressWidth(width: Int) {
        llProgress.layoutParams.width = width
        layoutContent.requestLayout()
    }

    open var type: Int = 0
        set(type) {
            setCardViewColor(type)
        }


    private fun setCardViewColor(type: Int) {
        val backgroundImageArrow: Drawable?
        val backgroundProgress: Drawable?
        val backgroundWrapProgress: Drawable?
        val iconImageArrow: Drawable?
        val borderColor: Int
        val textColor: Int

        if (type == 0) {
            backgroundImageArrow = ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_blue, null)
            backgroundProgress = ResourcesCompat.getDrawable(resources, R.drawable.card_view_progress_blue, null)
            backgroundWrapProgress = ResourcesCompat.getDrawable(resources, R.drawable.border_progress_item_blue, null)
            borderColor = ContextCompat.getColor(context, R.color.card_view_border_blue)
            textColor = ContextCompat.getColor(context, R.color.card_view_text_blue)
            iconImageArrow = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down_blue, null)
        } else {
            backgroundImageArrow = ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_yellow, null)
            backgroundProgress = ResourcesCompat.getDrawable(resources, R.drawable.card_view_progress_yellow, null)
            backgroundWrapProgress = ResourcesCompat.getDrawable(resources, R.drawable.border_progress_item_yellow, null)
            borderColor = ContextCompat.getColor(context, R.color.card_view_border_yellow)
            textColor = ContextCompat.getColor(context, R.color.card_view_text_yellow)
            iconImageArrow = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down_yellow, null)
        }

        ivItem.borderColor = borderColor
        tvTitle.setTextColor(textColor)
        tvQuantity.setTextColor(textColor)
        tvRemainder.setTextColor(textColor)
        tvPrice.setTextColor(textColor)
        tvTotal.setTextColor(textColor)
        tvTotalRemain.setTextColor(textColor)
        ivArrow.background = backgroundImageArrow
        ivArrow.setImageDrawable(iconImageArrow)
        wrapProgress.background = backgroundWrapProgress
        llProgress.background = backgroundProgress
    }
}

