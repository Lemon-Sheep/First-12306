package com.example.a12306f.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12306f.R;
import com.example.a12306f.a.Order;
import com.example.a12306f.adapter.OrderAdapter;
import com.example.a12306f.order.TicketNeedPayActivity;
import com.example.a12306f.order.TicketOrderPayedActivity;
import com.example.a12306f.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragment extends Fragment {

    private TextView tvWaitToPay, tvAll;
    private ListView lvOrder;

    private List<Map<String, Object>> data;
    private OrderAdapter orderAdapter;
    private int status = 0;//未支付订单

//    private TicketNeedPayActivity ticketNeedPayActivity = new TicketNeedPayActivity();

    private String[] orderId = {"订单编号:101943", "订单编号:101944", "订单编号:101945"};
    private String[] orderStatus = {"未支付", "已支付", "取消"};
    private String[] orderTrainNo = {"G108", "D3", "D5"};
    private String[] orderDateFrom = {"2019-7-30", "2019-8-5", "2019-8-30"};
    private String[] orderStationFrom = {"北京-成都 2人", "北京-上海 1人", "武汉-成都 2人"};
    private String[] orderPrice = {"￥1300", "￥510", "￥998"};
    private int[] orderFlag = {R.drawable.forward_25, R.drawable.forward_25, R.drawable.flg_null};

    public OrderFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticket_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView();

        tvWaitToPay = view.findViewById(R.id.tv_order_wait_to_Pay);
        tvAll = view.findViewById(R.id.tv_order_all);
        lvOrder = view.findViewById(R.id.lv_order);

        tvWaitToPay.setOnClickListener(new OrderHandle());
        tvAll.setOnClickListener(new OrderHandle());

        data = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId[i]);
            map.put("orderStatus", orderStatus[i]);
            map.put("orderTrainNo", orderTrainNo[i]);
            map.put("orderDateFrom", orderDateFrom[i]);
            map.put("orderStationFrom", orderStationFrom[i]);
            map.put("orderPrice", orderPrice[i]);
            map.put("orderFlag", orderFlag[i]);
            data.add(map);
        }


//        adapter = new SimpleAdapter(
//                getActivity(),
//                data,
//                R.layout.item_ticket_order,
//                new String[]{"orderId","orderStatus","orderTrainNo","orderDateFrom","orderStationFrom","orderPrice"},
//                new int[]{R.id.tv_order,R.id.tv_order_status,R.id.tv_order_train_num,R.id.tv_order_date,R.id.tv_order_station,R.id.tv_order_price});

        orderAdapter = new OrderAdapter(getActivity(), data);
        lvOrder.setAdapter(orderAdapter);

        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //是否支付
//                if (data.get(position).get("orderStatus").toString().equals("未支付")){
//                    intent.putExtra("order",orders[position]);
//                    intent.setClass(getActivity(),TicketNeedPayActivity.class);
//                    intent.putExtra("order",orders[position]);
//                    startActivity(intent);
//                }else if (data.get(position).get("orderStatus").toString().equals("已支付")){
//                    intent.setClass(getActivity(), TicketOrderPayedActivity.class);
////                    intent.putExtra("order",orders[position]);
//                    startActivity(intent);
//                }
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), TicketNeedPayActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(), TicketOrderPayedActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private class OrderHandle implements View.OnClickListener {
        @Override
        public void onClick(View view1) {
            data.clear();
            switch (view1.getId()) {
                case R.id.tv_order_wait_to_Pay:
                    tvWaitToPay.setBackgroundResource(R.drawable.cab_background_bottom_mainbar);
                    tvAll.setBackgroundResource(0);
//                         status = 1;
//                          new OrderTask().execute();
//                          adapter.notifyDataSetChanged();
                    data = new ArrayList<>();
                    for (int i = 0; i < 1; i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("orderId", orderId[i]);
                        map.put("orderStatus", orderStatus[i]);
                        map.put("orderTrainNo", orderTrainNo[i]);
                        map.put("orderDateFrom", orderDateFrom[i]);
                        map.put("orderStationFrom", orderStationFrom[i]);
                        map.put("orderPrice", orderPrice[i]);
                        map.put("orderFlag", orderFlag[i]);
                        data.add(map);
                        orderAdapter = new OrderAdapter(getActivity(),data);
                        lvOrder.setAdapter(orderAdapter);
                    }
                    break;
                case R.id.tv_order_all:
                    tvWaitToPay.setBackgroundResource(0);
                    tvAll.setBackgroundResource(R.drawable.cab_background_bottom_mainbar);
                    for (int i = 0; i < orderId.length; i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("orderId", orderId[i]);
                        map.put("orderStatus", orderStatus[i]);
                        map.put("orderTrainNo", orderTrainNo[i]);
                        map.put("orderDateFrom", orderDateFrom[i]);
                        map.put("orderStationFrom", orderStationFrom[i]);
                        map.put("orderPrice", orderPrice[i]);
                        map.put("orderFlag", orderFlag[i]);
                        data.add(map);
                        orderAdapter = new OrderAdapter(getActivity(),data);
                        lvOrder.setAdapter(orderAdapter);
                    }
                    break;
            }
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        new OrderTask().execute();
    }
    class OrderTask extends AsyncTask<String,String,Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!NetworkUtils.checkNet(getActivity())) {
                Toast.makeText(getActivity(), "当前网络不可用", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        @Override
        protected Object doInBackground(String... strings) {
            return null;
        }
    }
}
