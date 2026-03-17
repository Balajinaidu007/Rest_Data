package com.vertex;
import java.util.List;

public class ExportRequest {
    private Data Data;

    public Data getData() { return Data; }
    public void setData(Data data) { Data = data; }
}

class Data {
    private Root Root;
    private List<String> TotalIds;
    private List<String> OptionalIds;

    public Root getRoot() { return Root; }
    public void setRoot(Root root) { Root = root; }

    public List<String> getTotalIds() { return TotalIds; }
    public void setTotalIds(List<String> totalIds) { TotalIds = totalIds; }

    public List<String> getOptionalIds() { return OptionalIds; }
    public void setOptionalIds(List<String> optionalIds) { OptionalIds = optionalIds; }
}

class Root {
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}