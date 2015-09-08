package example.com.touchdelegateandroidpointerdown;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageView mTarget;
    private ToggleButton mTouchDelegateEnableToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTarget = (ImageView) findViewById(R.id.iv_target);
        mTouchDelegateEnableToggle = (ToggleButton) findViewById(R.id.tb_touchdelegate_enable);
        setupToggle();


        mTarget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "Action Down - Touch Delegate Enabled: " + mTouchDelegateEnableToggle.isChecked(), Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Toast.makeText(MainActivity.this, "Action Pointer Down - Touch Delegate Enabled: " + mTouchDelegateEnableToggle.isChecked(), Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return true;
            }
        });

    }

    private void setupToggle() {
        mTouchDelegateEnableToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Rect hitRect = new Rect();
                    mTarget.getHitRect(hitRect);
                    hitRect.left -= 400;
                    hitRect.top -= 400;
                    hitRect.right += 400;
                    hitRect.bottom += 400;
                    TouchDelegate delegate = new TouchDelegate(hitRect, mTarget);
                    ((ViewGroup)mTarget.getParent()).setTouchDelegate(delegate);

                } else {
                    ((ViewGroup)mTarget.getParent()).setTouchDelegate(null);
                }
            }
        });
    }


}
