package org.docksidestage.javatry.basic.st6.dbms;

public abstract class Absql {
    public String buildPagingQuery(int pageSize, int pageNumber){
        int offset = pageSize * (pageNumber - 1);
        return prefixOffset() + offset + prefixPagesize() + pageSize;
    }
    protected abstract String prefixOffset();
    protected abstract String prefixPagesize();

}
