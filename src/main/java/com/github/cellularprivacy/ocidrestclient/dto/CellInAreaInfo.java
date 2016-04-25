package com.github.cellularprivacy.ocidrestclient.dto;

import java.util.List;

public class CellInAreaInfo extends Count {
    private List<CellInfo> cells;

    public List<CellInfo> getCells() {
        return cells;
    }

    public void setCells(List<CellInfo> cells) {
        this.cells = cells;
    }
}
