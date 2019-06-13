package umaa.uu.mca.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element element=new Element();

        element.setTitle("Who We Are");
        View contactPage=new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.app_logo)
                .setDescription("Our Evolution ?\n" +
                        "The Utkal MCA Alumni Association (UMAA) exist to help you to stay connected to department and alumni, wherever you are in your life now.\n" +
                        "\n" +
                        "Its members include recipients of MCA degrees granted by the Department of Computer Science and Applications, Utkal University as well as the members of all MCA Faculties. The purpose of the association is to promote the strength of the department and to establish a strong relationship between department and its alumni. The association helps alumni connect with the University, department, faculties, students and with fellow alumni through a wealth of alumni events/gatherings, online learnings, association activities, and reunions. The main work of the Association is carried out by committees. These committees covers areas such as professional learning/development, career opportunities, and reunions.\n" +
                        "\n" +
                        "The Utkal MCA Alumni Association (UMAA) helps to create the vibrant connection among teachers, alumni, students and friends. UMAA provide opportunities for professional growth for alumni and students. All MCA students enter into the alumni association upon graduation and remain part of the community for life.\n" +
                        "\n" +
                        "The UMAA seeks to represent the diversity and richness of former students, always with a deep connection to current students. Through intellectual programming, networking and community-building, annual traditions that alumni can return to and opportunities to lead and serve, the UMAA provides rich opportunities to stay involved, have an impact and promote the success of Department.\n" +
                        "\n" +
                        "All alumni can volunteer with the Department and the UMAA by participating in Reunion Committees, mentoring students, joining technical sessions, hosting events, interviewing prospective students, participating in social activities, offering internships to students and much more. The opportunities are diverse, allowing all alumni to participate and lead in ways best suited to their lives.\n" +
                        "\n" +
                        "The Association is governed by alumni from all over the world.\n" +
                        "\n" +
                        "Out Aims & Objectives\n" +
                        "1.To provide a forum to establish a link between the alumni, staff and students of the Institute.\n" +
                        "2.To enable the alumni to participate in activities which would contribute to the general development of the Institute.\n" +
                        "3.To keep the alumni abreast of scientific and technological developments of national importance.\n" +
                        "4.To help the alumni with their technical problems.\n" +
                        "5.To Institute prizes and scholarships, and render financial aid to deserving students of the Institute.\n" +
                        "6.To contribute towards the welfare of the alumni.\n" +
                        "7.To further such other aims as the General Body may decide from time to time.")
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
                Toast.makeText(AboutUsActivity.this,copyRightString, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRight;
    }
}
