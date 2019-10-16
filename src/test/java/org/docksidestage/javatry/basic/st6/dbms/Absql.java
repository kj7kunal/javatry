package org.docksidestage.javatry.basic.st6.dbms;

// TODO fix class name to be more easy to understand by zaya 2019/10/16
// abstract Absql(=AbstractSql?) class has 2 abstract
public abstract class Absql {
    public String buildPagingQuery(int pageSize, int pageNumber){
        int offset = pageSize * (pageNumber - 1);
        return prefixOffset() + offset + prefixPagesize() + pageSize;
    }
    protected abstract String prefixOffset();
    protected abstract String prefixPagesize();

}
