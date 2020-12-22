package relations;

import java.util.ArrayList;
import java.util.Hashtable;

public class RelationUnit {
    public String relationName;
    public String primaryKeyName;

    public boolean isRoot;
    public boolean isLeaf;

    public RelationUnit parentRelation;
    public ArrayList<RelationUnit> childRelations;
    public int childRelationsNum;
    public ArrayList<IndexOfRelationAndChildRelation> indexOfRandRc;

    public Hashtable<Long, Object> tuplesIndex;
    public Hashtable<Long, Object> liveTuplesIndex;
    public Hashtable<Long, Object> nonliveTuplesIndex;
    public Hashtable<Long, Integer> s_counter;

    public int assertionKeyNum = 0;
    public ArrayList<Hashtable<Long, AssertionKeyValue>> assertionKey;
    public Hashtable<Integer, Integer> childRelationAndAssertionKeyPool;

    public int getAssertionKeyNum() {
        return assertionKeyNum;
    }

    public void setAssertionKeyNum(int assertionKeyNum) {
        this.assertionKeyNum = assertionKeyNum;
    }

    public ArrayList<Hashtable<Long, AssertionKeyValue>> getAssertionKey() {
        return assertionKey;
    }

    public void setAssertionKey(ArrayList<Hashtable<Long, AssertionKeyValue>> assertionKey) {
        this.assertionKey = assertionKey;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public RelationUnit getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(RelationUnit parentRelation) {
        this.parentRelation = parentRelation;
    }

    public ArrayList<RelationUnit> getChildRelations() {
        return childRelations;
    }

    public void setChildRelations(ArrayList<RelationUnit> childRelations) {
        this.childRelations = childRelations;
    }

    public int getChildRelationsNum() {
        return childRelationsNum;
    }

    public void setChildRelationsNum(int childRelationsNum) {
        this.childRelationsNum = childRelationsNum;
    }

    public ArrayList<IndexOfRelationAndChildRelation> getIndexOfRandRc() {
        return indexOfRandRc;
    }

    public void setIndexOfRandRc(ArrayList<IndexOfRelationAndChildRelation> indexOfRandRc) {
        this.indexOfRandRc = indexOfRandRc;
    }

    public Hashtable<Long, Object> getTuplesIndex() {
        return tuplesIndex;
    }

    public void setTuplesIndex(Hashtable<Long, Object> tuplesIndex) {
        this.tuplesIndex = tuplesIndex;
    }

    public Hashtable<Long, Object> getLiveTuplesIndex() {
        return liveTuplesIndex;
    }

    public void setLiveTuplesIndex(Hashtable<Long, Object> liveTuplesIndex) {
        this.liveTuplesIndex = liveTuplesIndex;
    }

    public Hashtable<Long, Object> getNonliveTuplesIndex() {
        return nonliveTuplesIndex;
    }

    public void setNonliveTuplesIndex(Hashtable<Long, Object> nonliveTuplesIndex) {
        this.nonliveTuplesIndex = nonliveTuplesIndex;
    }

    public Hashtable<Long, Integer> getS_counter() {
        return s_counter;
    }

    public void setS_counter(Hashtable<Long, Integer> s_counter) {
        this.s_counter = s_counter;
    }
}
