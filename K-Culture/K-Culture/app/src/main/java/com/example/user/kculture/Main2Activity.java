package com.example.user.kculture;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.display.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.user.kculture.DBHelper.DATABASE_NAME;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQUEST_IMAGE_CAPTURE=1;

    public DBHelper mDB;

    private ArrayList<MyData> mData;
    /*
    private MyData[] myData=
            {
                    new MyData(R.drawable.jypnew,"JYP 신사옥","설명"),
                    new MyData(R.drawable.yg,"YG 사옥","설명"),
                    new MyData(R.drawable.smcoex,"SM coexartium","설명"),
                    new MyData(R.drawable.aori,"아오리라멘","설명"),
                    new MyData(R.drawable.soulcup,"Soul Cup in JYP","JYP신사옥에 위치한 유기농 카페이다"),
                    new MyData(R.drawable.hanok,"북,서촌 한옥마을","도심와 전통적인 한옥의 만남"),
                    new MyData(R.drawable.gyeongbok,"경복궁","세종로에 위치한 조선시대의 정궁이다"),
                    new MyData(R.drawable.lottefitin,"롯데피트인 동대문점","K-POP 가수들의 홀로그램 콘서트를 볼 수 있는곳이다."),
                    new MyData(R.drawable.kstarroad,"강남한류스타거리","한류의 중심거리이고 한류스타들이 많이 오가는 거리이다."),
                    new MyData(R.drawable.gstarzone,"G+Star Zone","매년 새로운 한류스타의 문화로 꾸며진 공간이다"),
                    new MyData(R.drawable.coffeeprince,"커피프린스 1호점","커피프린스 촬영지이다")
            };*/
    private ListView mList;
    private NewAdapter mAdapter;

    void insertDB()
    {

        mData.add(new MyData(R.drawable.jypnew, mDB.insertPlace("JYP 신사옥",
                "JYP Entertainment",
                " 2018년에 입주한 JYP의 새로운 사옥이다 \n\n Website:jype.com \n Phone: 02-2225-8100 \n 영업시간: 오전9시~오후5시", "It is the new building of JYP which moved in 2018. \n\n Website:jype.com \n Phone: 02-2225-8100 \n Open Time: 9AM~5PM",
                37.5244022, 127.1292196), MyData.FILTER_PLACE));

        mData.add(new MyData(R.drawable.yg, mDB.insertPlace("YG 신사옥",
                "YG Entertainment",
                " YG엔터테인먼트의 사옥이다 \n\n Website: ygfamily.com \n Phone: 02-3142-1104 \n 영업시간: 일요일 제외 24시간", " Building of YG Entertainment \n\n Website: ygfamily.com \n Phone: 02-3142-1104 \n Open Time: 24Hours Except Sunday",
                37.5488750, 126.9081817), MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.smcoex, mDB.insertPlace("SM coexartium",
                "SM coexartium",
                " Coex에 위치한 SM아티움입니다. SM아트스트들의 굿즈들을 살 수 있고 관련된 것들을 체험할수있습니다. \n\n Website: smtownland.com \n Phone: 02-6002-5811 \n 영업시간: 오전11시~오후10시", " SM's artium is where K-POP fans can experience variety activities related to SM artists \n\n Website: smtownland.com \n Phone: 02-6002-5811 \n Open Time: 11AM~10PM",
                37.5103985, 127.0612148), MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.aori, mDB.insertPlace("아오리라멘",
                "Aori Ramen",
                " 빅뱅 또는 승리의 팬이라면 꼭 가야하는 라멘집이다. \n\n Website: N/A \n Phone: 02-518-3767 \n 영업시간: 오전11시~오후11시", " Restaurant that belongs to Seungri of BigBang \n\n Website: N/A \n Phone: 02-518-3767 \n Open Time: 11AM~11PM",
                37.5240811, 127.0411273), MyData.FILTER_FOOD));
        mData.add(new MyData(R.drawable.soulcup, mDB.insertPlace("Soul Cup in JYP",
                "Soul Cup in JYP",
                " JYP신사옥에 위치한 유기농 카페이다 \n\n Website:jype.com \n Phone: 02-2225-8100 \n 영업시간: 오전9시~오후5시", " It is an organic cafe located in JYP's new building. \n\n Website:jype.com \n Phone: 02-2225-8100 \n Open Time: 9AM~5PM",
                37.5244022, 127.1292196), MyData.FILTER_PLACE | MyData.FILTER_FOOD));
        mData.add(new MyData(R.drawable.hanok, mDB.insertPlace("북,서촌 한옥마을",
                "Bukchon&Seochon Hanok Village",
                " 도심와 전통적인 한옥의 만남 \n\n Website: hanok.go.kr \n Phone: 02-2133-1371 \n 영업시간: 9AM~5PM", " A traditional Korean Village \n\n Website: hanok.go.kr \n Phone: 02-2133-1371 \n Open Time: 9AM~5PM",
                37.5790905, 126.9864536),MyData.FILTER_TRADITION));
        mData.add(new MyData(R.drawable.gyeong1visitseoul, mDB.insertPlace("경복궁",
                "Gyeongbokgung",
                " 세종로에 위치한 조선시대의 정궁이다 \n\n Website: royalpalace.go.kr \n Phone: 02-3700-3900 \n 영업시간: 9AM~6PM", " Main palace of the Joseon Dynasty. \n * TIP!: It's located next to Bukchon&Seochon Hanok Village \n\n Website: royalpalace.go.kr \n Phone: 02-3700-3900 \n Open Time: 9AM~6PM",
                37.5772746, 126.9769070),MyData.FILTER_TRADITION));
        mData.add(new MyData(R.drawable.lottefitin, mDB.insertPlace("롯데피트인 동대문점",
                "Lotte FITIN Dong Dae Moon",
                " K-POP 가수들의 홀로그램 콘서트를 볼 수 있는곳이다. \n\n Website: lottefitin.com \n Phone: 02-6262-4000 \n 영업시간: 11AM~12PM", " This is where you can see the hologram concert of K-POP singers. \n\n Website: lottefitin.com \n Phone: 02-6262-4000 \n Open Time: 11AM~12PM",
                37.5657527, 127.0070725),MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.kstarroad, mDB.insertPlace("강남한류스타거리",
                "K-Star Road",
                " 한류의 중심거리이고 한류스타들이 많이 오가는 거리이다. \n\n Website: http://korean.visitseoul.net/Hallyu ",
                " It is the center of Hallyu and it is a street where many Korean stars come and go. \n\n Website: http://korean.visitseoul.net/Hallyu ",
                37.5306420, 127.0307130),MyData.FILTER_PLACE | MyData.FILTER_FOOD));
        mData.add(new MyData(R.drawable.gstarzone, mDB.insertPlace("G+Star Zone",
                "G+Star Zone",
                " 매년 새로운 한류스타의 문화로 꾸며진 공간이다 \n\n Phone: 02-3423-5789", " It is a space decorated with the culture of the new Korean star every year \n\n Phone: 02-3423-5789",
                37.5256401, 127.0400109),MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.coffeeprince, mDB.insertPlace("커피프린스 1호점",
                "Coffee Prince 1st Place",
                " 커피프린스 촬영지이다 \n\n Phone: 02-391-4737 \n 영업시간: 11AM~10PM", " It is a shooting place of drama 'Coffee Prince' \n\n Phone: 02-391-4737 \n Open Time: 11AM~10PM",
                37.5545280, 126.9292998),MyData.FILTER_PLACE | MyData.FILTER_FOOD));
        mData.add(new MyData(R.drawable.seoulro7017, mDB.insertPlace("서울로7017",
                "Seoullo 7017",
                " 서울역 고가도로의 재탄생, 서울로 \n야경이 굉장히 아름다운 곳 밤에 가시는 것을 추천드립니다." , " Seoul Highway that changed to where people can walk \n TIP! Go at night Beautiful Night View",
                37.5572862, 126.9707281),MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.dmcvisitseoulnet, mDB.insertPlace("DMC(디지털미디어단지)",
                "DMC",
                " 한국 방송국등 엔터테인먼트와 관련된 곳이 밀집해있는 곳이다", " It is a place where Entertainment-related places such as Korean broadcasting stations are concentrated. \n Also where Korean Variety Shows or Music Shows gets filmed",
                37.5810812, 126.8909025),MyData.FILTER_PLACE));
        mData.add(new MyData(R.drawable.lottevisitseoul, mDB.insertPlace("제2롯데월드 서울스카이",
                "Second Lotte World (Seoul Sky)",
                " 123층 규모의 초고층 건물로 스카이워크라운지에서 뻥뚤린 유리바닥을 체험할 수 있다. \n\n Website: seoulsky.lotteworld.com/main/index.do \n Phone: 02-1661-2000 \n\n 영업시간: 월~목 오전10시~오후10시 | 금,토,연휴 오전 10시~오후11시 \n\n 가격: 어른(13세 이상) 27000원 | 어린이(12개월~12세 이하) 24000원 | FAST PASS 50000원", " 123 floor builiding where visitors can experience glass floors come from Skywalk Lounge \n \n Website: seoulsky.lotteworld.com/main/index.do \n Phone: 02-1661-2000 \n\n Open Time: Mon~Thu 10AM~10PM | Fri,Sat,Holiday 10AM~11PM \n\n Price: Adult(Above 13yrs Old) 27000WON | Child(12Months~12yrs Old Below) 24000WON | FAST PASS 50000WON ",
                37.5131179, 127.1035243),MyData.FILTER_PLACE));


    }

    void testDb()
    {
        for(MyData data: mData)
        {
            Log.d("SQL", "PrimaryKey: " + data.pkey);
            Cursor res = mDB.getData(data.pkey);
            Log.d("SQL", "Column Count: " + res.getColumnCount());

            Log.d("SQL", "NAME : "+res.getColumnNames()[0]+
                    res.getColumnNames()[1]
            +res.getColumnNames()[2]
                    +res.getColumnNames()[3]
                            +res.getColumnNames()[4]);
            //Log.d("SQL", "NAME : "+res.getString(1));
            Log.d("SQL", " "+res.getString(1));

        }

    }

    String loadCultureJSON()
    {
       String json = null;
       try{
           InputStream is = getAssets().open("historyinfo.json");
           int size = is.available();
           byte [] buffer = new byte[size];
           is.read(buffer);
           is.close();
           json = new String(buffer, "UTF-8");

       }catch (IOException ex)
       {
           ex.printStackTrace();
           return null;
       }
        return json;
    }

    public DBHelper getDB()
    {
        return mDB;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mList=(ListView)findViewById(R.id.kculture_list);


        //mAdapter=new NewAdapter(this,myData);
        mDB = new DBHelper(this);
        mData = new ArrayList<MyData>();

        insertDB();  // mDB --> mData
        Log.d("Main2","insertDB : Row "+ mDB.numberOfRows());
        testDb();

        // DB를 만들고 나서 아답터에 넣어야 함.
        mAdapter = new NewAdapter(this, mData);
        mList.setAdapter(mAdapter);
        Utility.setListViewHeightBasedOnChildren(mList);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(Main2Activity.this,myData[position].name+"선택",
                 //       Toast.LENGTH_SHORT).show();

                //Cursor res = mDB.getData(mData.get(position).pkey);
                MyData data = (MyData)mAdapter.getItem(position);
                Cursor res = mDB.getData(data.pkey);
                //Toast.makeText(Main2Activity.this,res.getString(2)+"선택",
                            //     Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),InfoActivity.class);

                    //String value = myData[position].name;
                    String value = mAdapter.getName(position);
                    intent.putExtra("name", value);
                    intent.putExtra("pkey", mAdapter.getPkey(position));

                    intent.putExtra("pic", data.pic);
                    startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void OnDestroy()
    {
        this.deleteDatabase(DATABASE_NAME);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(getApplicationContext(),SettingScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            //카메라실행
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(getApplicationContext(),SettingScreen.class);
            startActivity(intent);

        }else if (id==R.id.call){
            String posted_by = "1330";

            String uri = "tel:" + posted_by.trim() ;
            Intent intentDial = new Intent(Intent.ACTION_DIAL);
            intentDial.setData(Uri.parse(uri));
            startActivity(intentDial);

        } else if (id == R.id.nav_share) {
            String message = "Try K-Culture, Best Experience in Seoul";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(share, "Share K-Culture"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setCheck(int filter)
    {
        /*(
        if(mList.getTextFilter() == null)
        {
            mList.setFilterText("");
        }
        String strFilter = (String) mList.getTextFilter();

        String strFilter  = new String();
        if(strFilter.isEmpty())
        {
            strFilter = "";
        }
        if(filter == MyData.FILTER_FOOD && !strFilter.contains("FOOD"))
        {
            strFilter += "FOOD";
        }
        if(filter == MyData.FILTER_PLACE && !strFilter.contains("PLACE"))
        {
            strFilter += "PLACE";
        }
        if(filter == MyData.FILTER_TRADITION && !strFilter.contains("TRAD"))
        {
            strFilter += "TRAD";
        }
        mList.clearTextFilter();
        mList.setFilterText(strFilter);
        */
        mAdapter.addFilter(filter);
        mAdapter.notifyDataSetChanged();
    }

    private void setUnCheck(int filter)
    {
        mAdapter.deleteFilter(filter);

        mAdapter.notifyDataSetChanged();
        /*
        String strFilter = new String();
        if(filter == MyData.FILTER_FOOD && strFilter.contains("FOOD"))
        {
            strFilter.replace("FOOD","");
        }
        if(filter == MyData.FILTER_PLACE && strFilter.contains("PLACE"))
        {
            strFilter.replace("PLACE","");
        }
        if(filter == MyData.FILTER_TRADITION && strFilter.contains("TRAD"))
        {
            strFilter.replace("TRAD","");
        }
        mList.clearTextFilter();
        mList.setFilterText(strFilter);
        */

    }
    public void OnCheckboxClicked(View view)
    {
        boolean checked=((CheckBox)view).isChecked();

        switch(view.getId()) {
            case R.id.checkBox_food:
                if (checked) {
                    setCheck(MyData.FILTER_FOOD);
                    //Toast.makeText(getApplicationContext(), "음식 선택",
                          //  Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_FOOD);
                    //Toast.makeText(getApplicationContext(), "음식 선텍해제",
                           // Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkBox_new:
                if (checked) {
                    setCheck(MyData.FILTER_TRADITION);
                   // Toast.makeText(getApplicationContext(), "new 선택",
                          //  Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_TRADITION);
                   // Toast.makeText(getApplicationContext(), "new 선텍해제",
                           // Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkBox_old:
                if (checked) {
                    setCheck(MyData.FILTER_PLACE);
                  //  Toast.makeText(getApplicationContext(), "old 선택",
                           // Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_PLACE);
                   // Toast.makeText(getApplicationContext(), "old 선텍해제",
                           // Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
