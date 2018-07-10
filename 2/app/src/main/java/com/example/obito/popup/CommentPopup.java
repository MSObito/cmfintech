package com.example.obito.popup;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.obito.R;
import com.example.obito.fragment.CommentFragment;
import com.example.obito.model.NewsBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import razerdp.basepopup.BasePopupWindow;

public class CommentPopup extends BasePopupWindow implements View.OnClickListener{

    private EditText commentEditText;
    private Button sendButton;

    public CommentPopup(Context context) {
        super(context);
        commentEditText=(EditText)findViewById(R.id.makeCommentEditText);
        sendButton=(Button)findViewById(R.id.sendButton);
        setViewClickListener(this,sendButton);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;

    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.comment);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popupAnimation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendButton:
                String comment= commentEditText.getText().toString();
                NewsBean newsBean=new NewsBean();
                NewsBean.Comments comments=newsBean.new Comments();
                comments.setText(comment);
                comments.setThumbUp(0);
                String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                comments.setDateTime(date);
                comments.setIcon("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTExIDc5LjE1ODMyNSwgMjAxNS8wOS8xMC0wMToxMDoyMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RDlBMTExMzNFREQzMTFFNUFEQUJFRkE1QUYxQzU1QjIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RDlBMTExMzRFREQzMTFFNUFEQUJFRkE1QUYxQzU1QjIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpEOUExMTEzMUVERDMxMUU1QURBQkVGQTVBRjFDNTVCMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpEOUExMTEzMkVERDMxMUU1QURBQkVGQTVBRjFDNTVCMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pjrl9nQAAAM7SURBVHjaxFk7a5RBFJ2dfT9sjJWihRLUVgKptVYxYATFVlGj/gUR9BcoKiJoERMxgo2VgtaB2CtarcRG12Xf713v2dxP5HO/b+bOPjxwSJHdmZM7d+6cexMpFovKAZp4jHicuEA8TNxLzPHvq8TvxM/Ej8QP/LMv3SgiFLiPeI14kXhAuFeeuEp8QNyetMDdxNvES8SkGg8t4hPiLWLB5qhMOMtHdX0C4hSvsUL8RFweR2CMj2ODuEdNHljzJfEh7yUSmCG+Jl5V08cV3itjKzBBXCeeVLMD9nrBexsF3iOeVrPHKeJ9k8DzxMvq/wFV4kJQmZnj2zonXbXdbqtms6l6vd7O7YrFVCqVUvF43EUkSs9R4g9/BO+6iKvVaqparaput6sGg8GQnU5HVSoV1Wg0XARCwx1/BPcTv45K0jAgavV6PfQzuVxOJRIJ8aEQ5/H6eBFckYpDpGwiZPoDAgAtN7wj1vy2ivMOIk3o9/vDI3cALksU4hbZBIjgXQgbID8dAHe0qNkyiYHISNLBESc0+zm5IdR6Kp/1YUGz2RRHDzlo7a9aLdcozms+ayW9wZIjluSr3yBD4C7pt6LR6JC2wMsSiURcBOackyOZtPeuePZcAYEVly9iUxuR6XTa5SXxUNXcfTnBxgw4GgYP25odjBMQmbBchDjk3xj4AoFb46yQyWScfmeJLc1NtTPC6qGkVgbgPQRuShppvwAU4TA75vgOK74bm5rHEatSowCTCpoKerlcHppaSWFnPMdWIsOKaCAqrkeHsoTyZFHk/zGs34hPw4TBwiMa4+QV0qFUKg0jb3j+nvEsx9w0wTU79hZGZLPZUcUeTdMR4k9/01Tg+csfwAlPS5zXcI24RDc9caP6YnT3j70ExwLThm8P7L1mmiygWXmLy+Bw88RALnKpeuc1SiaBbYreEgl8M6txAqUR9jrDt9c83SJxdYjk0di08YhOaomiWLedbnkhR/Zi3Lv8d9JOEFjznNoZ8XWRUtYCfQ7kFfctmDy1JiCsxWuhlGyYukQdZDJ9+MUJfJDnJnkHYXme/xzitQo2nV/gEB0vRshbG/RviKxXPZTw3xCwZqNag98CDABDuWjqjbQSHwAAAABJRU5ErkJggg==");
                comments.setName("本机");
                CommentFragment.commentAdapter.addData(CommentFragment.commentAdapter.getCommentsList().size(),comments);
                dismiss();
                break;
        }

        }
}
