package com.example.msalary.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowResult {

	 public int requestCode;
     /**���������صĴ���ֵ*/
     public int resultCode;

     public List<? extends ShowResult> list = new ArrayList<ShowResult>();
     /**��������*/
     public Map<String,String> map = new HashMap<String,String>();
}
