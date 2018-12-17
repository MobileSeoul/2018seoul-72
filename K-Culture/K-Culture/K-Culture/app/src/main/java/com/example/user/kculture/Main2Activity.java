package com.example.user.kculture;

import android.content.Intent;
import android.os.Bundle;
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

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
            };
    private ListView mList;
    private NewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mList=(ListView)findViewById(R.id.kculture_list);
        mAdapter=new NewAdapter(this,myData);
        mList.setAdapter(mAdapter);
        Utility.setListViewHeightBasedOnChildren(mList);

        myData[0].setCategory(MyData.FILTER_PLACE); //카테고리화
        myData[1].setCategory(MyData.FILTER_PLACE);
        myData[2].setCategory(MyData.FILTER_PLACE);
        myData[3].setCategory(MyData.FILTER_FOOD);
        myData[4].setCategory(MyData.FILTER_PLACE | MyData.FILTER_FOOD);
        myData[5].setCategory(MyData.FILTER_TRADITION);
        myData[6].setCategory(MyData.FILTER_TRADITION);
        myData[7].setCategory(MyData.FILTER_PLACE);
        myData[8].setCategory(MyData.FILTER_PLACE | MyData.FILTER_FOOD);
        myData[9].setCategory(MyData.FILTER_PLACE);
        myData[10].setCategory(MyData.FILTER_PLACE | MyData.FILTER_FOOD);




        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this,myData[position].name+"선택",
                        Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),InfoActivity.class);

                    String value = myData[position].name;
                    intent.putExtra("position", value);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
                    Toast.makeText(getApplicationContext(), "음식 선택",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_FOOD);
                    Toast.makeText(getApplicationContext(), "음식 선텍해제",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkBox_new:
                if (checked) {
                    setCheck(MyData.FILTER_TRADITION);
                    Toast.makeText(getApplicationContext(), "new 선택",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_TRADITION);
                    Toast.makeText(getApplicationContext(), "new 선텍해제",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkBox_old:
                if (checked) {
                    setCheck(MyData.FILTER_PLACE);
                    Toast.makeText(getApplicationContext(), "old 선택",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    setUnCheck(MyData.FILTER_PLACE);
                    Toast.makeText(getApplicationContext(), "old 선텍해제",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
