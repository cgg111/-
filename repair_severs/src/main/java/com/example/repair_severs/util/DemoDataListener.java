package com.example.repair_severs.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.example.repair_severs.entity.DemoData;
import com.example.repair_severs.entity.Readfedexmingxi;

import java.util.List;

public class DemoDataListener<T> implements ReadListener<T> {

    private List<T> dataList;

    // 构造函数，接受一个泛型列表
    public DemoDataListener(List<T> dataList) {
        this.dataList = dataList;
    }

    // 读取每一行数据并添加到列表中
    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);  // 将每一行数据添加到列表中
    }

    // 所有数据读取完成后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据读取完成，数据量：" + dataList.size());
    }

    // 获取数据列表
    public List<T> getDataList() {
        return dataList;
    }
}
