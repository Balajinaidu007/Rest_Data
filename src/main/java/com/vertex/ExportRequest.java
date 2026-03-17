package com.vertex;
import java.util.List;

public class ExportRequest {
    private Data data;

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}

class Data {
    private Root root;
    private List<String> totalIds;
    private List<String> optionalIds;

    public Root getRoot() { return root; }
    public void setRoot(Root root) { this.root = root; }

    public List<String> getTotalIds() { return totalIds; }
    public void setTotalIds(List<String> totalIds) { this.totalIds = totalIds; }

    public List<String> getOptionalIds() { return optionalIds; }
    public void setOptionalIds(List<String> optionalIds) { this.optionalIds = optionalIds; }
}

class Root {
    private String id;
    private String type; 

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}