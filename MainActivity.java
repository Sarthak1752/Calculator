package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView resultTV,solutionTV;
MaterialButton Buttonc,ButtonBracketOpen,ButtonBracketClose,Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Buttondiv,Buttonplus,Buttonminus,Buttonmul,Buttonequal,Buttonac,Buttondot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_TV);
        solutionTV = findViewById(R.id.solution_TV);
         Buttonc= findViewById(R.id.button_c);
         ButtonBracketOpen= findViewById(R.id.button_openbracket);
         ButtonBracketClose = findViewById(R.id.button_closebracket);
         Button0= findViewById(R.id.button_0);
         Button1 = findViewById(R.id.button_1);
         Button2 = findViewById(R.id.button_2);
         Button3 = findViewById(R.id.button_3);
         Button4 = findViewById(R.id.button_4);
         Button5 = findViewById(R.id.button_5);
         Button6 = findViewById(R.id.button_6);
         Button7 = findViewById(R.id.button_7);
         Button8 = findViewById(R.id.button_8);
         Button9 = findViewById(R.id.button_9);
         Buttonplus = findViewById(R.id.button_plus);
         Buttonminus = findViewById(R.id.button_minus);
         Buttondiv = findViewById(R.id.button_Div);
         Buttonequal = findViewById(R.id.button_equal);
         Buttonmul = findViewById(R.id.button_mult);
         Buttonac = findViewById(R.id.button_AC);
         Buttondot = findViewById(R.id.button_dot);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        solutionTV.setText(buttonText);
        String dataToCalculate = solutionTV.getText().toString();
        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

        }else{
            dataToCalculate=dataToCalculate+buttonText;
        }

        solutionTV.setText(dataToCalculate);
        String finalResult = getRslt(dataToCalculate);
        if(!finalResult.equals("Err")){
            resultTV.setText(finalResult);
        }
    }
    String getRslt(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResuilt=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResuilt.endsWith(".0")){
               finalResuilt = finalResuilt.replace(".0","");
           }
            return finalResuilt;
        }catch (Exception e){
            return "Err";
        }
    }
}
