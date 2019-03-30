package kr.co.woobi.imyeon.realmexam;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import kr.co.woobi.imyeon.realmexam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    private Realm mRealm = Realm.getDefaultInstance();
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //쿼리문만들기
        RealmResults<Person> results = mRealm.where(Person.class)
                .sort("age", Sort.DESCENDING)
                .findAll();


        mAdapter = new RecyclerAdapter(results);
        mBinding.recycler.setAdapter(mAdapter);

        mBinding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealm.beginTransaction();
                Person user = mRealm.createObject(Person.class);
                user.setName(mBinding.editName.getText().toString());
                user.setAge(Integer.parseInt(mBinding.editAge.getText().toString()));
                mRealm.commitTransaction();
            }
        });

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //확정
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //글자마다
                Log.d(TAG, "onQueryTextChange: " + s);
                RealmResults<Person> results = mRealm.where(Person.class)
                        .sort("age", Sort.DESCENDING)
                        .contains("name", s)
                        .findAll();
                mAdapter.updateData(results);
                return true;
            }
        });

// 삭제
        mBinding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removeName = mBinding.searchView.getQuery().toString();

                mRealm.beginTransaction();
                RealmResults<Person> results1 = mRealm.where(Person.class)
                        .equalTo("name", removeName)
                        .findAll();

                results1.deleteAllFromRealm();
                mRealm.commitTransaction();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
