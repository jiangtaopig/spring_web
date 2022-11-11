package com.zjt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyFactory {
    Logger logger = LoggerFactory.getLogger(MyFactory.class);

    @Resource
    private List<MyAbstractBuilder> myAbstractBuilderList;

    private Map<String, MyAbstractBuilder> myAbstractBuilderMap;

    public void doSth(String businessType) {
        MyAbstractBuilder builder = getBuilder(businessType);
        builder.setKeyWord("i am keyword");
        String str = "123";
    }


    public MyAbstractBuilder getBuilder(String businessType) {
        return myAbstractBuilderMap.get(businessType);
    }


    @PostConstruct
    public void initMap() {
        logger.info("------initMap----- myAbstractBuilderList size = " + myAbstractBuilderList.size());
        myAbstractBuilderMap = myAbstractBuilderList.stream().collect(Collectors.toMap(MyAbstractBuilder::getBusinessType, a -> a, (k1, k2) -> k1));
    }
}
