package com.example.comprehensiveapplication.wxlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprehensiveapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class weixinFragment extends Fragment {
    private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    private View view;
    private RecyclerView recyclerView;
    private WXLayouUserAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weixin, container, false);
        initRecyclerView();
        initData();
        // Inflate the layout for this fragment
        return view;
    }

    private void initData() {
        String[] imgSrc = {"https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110429.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110422.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110414.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110354.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110520.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110526.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110537.png",
                "https://zeeland-bucket.oss-cn-beijing.aliyuncs.com/images/20230427110530.png"

        };
        String[] cttName = {"user1", "user2", "user3","user4","user5","user6","user7","user8"};
        String[] cttTime = {"下午16：32","下午13：10","上午10：20","上午：9: 00","昨天","昨天","3月21日","2021年12月10日"};
        String[] cttMsg = {"hello","hi","what's up","what?","???","ok","fine","I will go sleep"};
        for (int i = 0; i < imgSrc.length; i++) {
            Map<String, Object> itemData = new HashMap<String, Object>();
            itemData.put("key1", imgSrc[i]);
            itemData.put("key2", cttName[i]);
            itemData.put("key3",cttTime[i]);
            itemData.put("key4",cttMsg[i]);
            data.add(itemData);
        }
    }
    private void initRecyclerView () {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.getItemAnimator().setChangeDuration(500);
        recyclerView.getItemAnimator().setMoveDuration(500);
        myAdapter = new WXLayouUserAdapter(getActivity(), data);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(manager);
    }

}