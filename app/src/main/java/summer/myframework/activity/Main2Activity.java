package summer.myframework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import summer.myframework.R;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        btn2.setBackgroundColor(Color.RED);
    }

    @OnClick(R.id.btn2)
    public void onViewClicked() {
        Toast.makeText(this, "fuck", Toast.LENGTH_SHORT).show();
    }
}
