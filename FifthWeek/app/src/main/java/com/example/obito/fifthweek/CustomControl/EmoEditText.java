package com.example.obito.fifthweek.CustomControl;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by obito on 2018/3/21.
 */

public class EmoEditText extends android.support.v7.widget.AppCompatEditText {

    //输入表情前的光标位置
    private int cursorPos;

    //输入表情前EditText中的文本
    private String inputAfterText;

    //是否重制了EditText的内容
    private boolean resetText;

    private Context mContext;

    public EmoEditText(Context context) {
        super(context);
        this.mContext=context;
        initEditText();
    }

    public EmoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        initEditText();
    }

    public EmoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initEditText();
    }

    //初始edittext控件
    private void initEditText(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!resetText){
                    cursorPos=getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，
                    // inputAfterText和s在内存中指向的是同一个地址，s改变了，
                    // inputAfterText也就改变了，那么表情过滤就失败了
                    inputAfterText=s.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!resetText){
                    if(count>=2){
                        CharSequence input=s.subSequence(cursorPos,cursorPos+count);
                        if(containsEmoji(input.toString())){
                            resetText=true;
                            Toast.makeText(mContext,"文本中包含非法字符", Toast.LENGTH_SHORT).show();
                            setText(inputAfterText);
                            CharSequence text=getText();
                            if(text instanceof Spannable){
                                Spannable spannable=(Spannable)text;
                                Selection.setSelection(spannable,text.length());
                            }
                        }
                    }
                }else{
                    resetText=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 判断文本中是否包含emoji表情
     * @param source
     * @return
     */
    private static boolean containsEmoji(String source){
        int len=source.length();
        for(int i=0;i<len;i++){
            char codePoint= source.charAt(i);
            if(!isEmojiCharacter(codePoint)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符是否为emoji表情
     * @param codePoint
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint){
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }
}
