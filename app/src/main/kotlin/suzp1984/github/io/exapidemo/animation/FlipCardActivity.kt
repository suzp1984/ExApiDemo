package suzp1984.github.io.exapidemo.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

import suzp1984.github.io.exapidemo.R

class FlipCardActivity : AppCompatActivity() {

    @BindView(R.id.flip_card)
    internal lateinit var flipCardView : View

    @BindView(R.id.animation)
    internal lateinit var animationBtn: Button

    @BindView(R.id.animator)
    internal lateinit var animatorBtn : Button


    var animator : Animator?  = null
    var toggle : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)

        ButterKnife.bind(this)

    }

    @OnClick(R.id.animator)
    fun animator() {
        stopAnimator()

        var startAngor : Float = 0.0f
        var endAngor   : Float = 90.0f
        if (flipCardView.rotationY > 0) {
            startAngor = 90.0f
            endAngor   = 0.0f
        }
        val animator = ObjectAnimator.ofFloat(flipCardView, "rotationY", startAngor, endAngor)
        animator.setDuration(2000);
        animator.interpolator = AccelerateInterpolator()
        animator.start()

        this.animator = animator
    }

    @OnClick(R.id.animation)
    fun animationClick() {
        var startAngor : Float = 0.0f
        var endAngor   : Float = 90.0f
        if (!toggle) {
            startAngor = 90.0f
            endAngor   = 0.0f
        }

        toggle = !toggle

        flipCardView.visibility = View.VISIBLE

        val animation = FlipAnimation(startAngor, endAngor, 0.0f, flipCardView.height / 2.0f)
        animation.duration = 2000
        animation.interpolator = AccelerateInterpolator()
        animation.fillAfter = true
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                // flipCardView.rotationY = endAngor
                flipCardView.clearAnimation()

                if (!toggle) {
                    flipCardView.visibility = View.INVISIBLE
                }
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })

        flipCardView.startAnimation(animation)
    }

    fun stopAnimator() {
        animator?.end()
    }

}
