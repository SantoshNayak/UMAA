package umaa.uu.mca.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element element=new Element();

        element.setTitle("Stay Connected with UMAA");
        View contactPage=new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.app_logo)
                .setDescription("Stay Connected with UMAA")
                .addFacebook("UtkalUniversityMCA")
                .addTwitter("UtkalMcaAlumni")
                .addYoutube("UC_0kghEW-HgPqKHoFpF-_mQ")
                .addWebsite("http://www.utkalmcaalumni.in")
                .addEmail("uumcaalumni@gmail.com")
                .addItem(createCopyright())
                .create();
        setContentView(contactPage);

    }

    private Element createCopyright() {
        Element copyRight=new Element();
        final String copyRightString=String.format("CopyRight %d by UMAA",Calendar.getInstance().get(Calendar.YEAR));
        copyRight.setTitle(copyRightString);
        copyRight.setIconDrawable(R.mipmap.ic_launcher);
        copyRight.setGravity(Gravity.CENTER);
        copyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUsActivity.this,copyRightString, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRight;
    }
}
