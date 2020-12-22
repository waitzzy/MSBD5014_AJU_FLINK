package relations;
import java.util.*;

public class RelationsManager {
    public Map<String, RelationUnit> relationMapPool;

    public Map<String, RelationUnit> getRelationMapPool() {
        return relationMapPool;
    }

    public Map<String, RelationUnit> setQueryNum(int queryNum) {
        relationMapPool = new Hashtable<>();
        switch (queryNum) {
            case 1:
                initQuery1RelationPool();
                break;
            case 3:
                initQuery3RelationPool();
                break;
            case 4:
                initQuery4RelationPool();
                break;
            default:
                relationMapPool.clear();
                break;
        }
        return relationMapPool;
    }

    private void initQuery1RelationPool() {
        relationMapPool.clear();
        RelationUnit r = new RelationUnit();

        r.setRelationName("lineitem");
        r.setPrimaryKeyName("l_orderkeylinenumber");
        r.isRoot = true;
        r.isLeaf = true;
        r.setParentRelation(null);
        r.setChildRelations(null);
        r.setChildRelationsNum(0);
        r.setTuplesIndex(new Hashtable<>());
        r.setLiveTuplesIndex(new Hashtable<>());
        r.setNonliveTuplesIndex(new Hashtable<>());
        r.setS_counter(new Hashtable<>());

        // set index of R and Rc
        r.setIndexOfRandRc(null);

        relationMapPool.put("lineitem", r);
    }
    private void initQuery3RelationPool() {
        relationMapPool.clear();
        RelationUnit r1 = new RelationUnit();
        RelationUnit r2 = new RelationUnit();
        RelationUnit r3 = new RelationUnit();

        r1.setRelationName("lineitem");
        r1.setPrimaryKeyName("l_orderkey&l_linenumber");
        r1.isRoot = true;
        r1.isLeaf = false;
        r1.setParentRelation(null);
        r1.setChildRelations(new ArrayList<>());
        r1.childRelations.add(r2);
//            r1.childRelations.add(r3);
        r1.setChildRelationsNum(1);
        r1.setTuplesIndex(new Hashtable<>());
        r1.setLiveTuplesIndex(new Hashtable<>());
        r1.setNonliveTuplesIndex(new Hashtable<>());
        r1.setS_counter(new Hashtable<>());

        r2.setRelationName("orders");
        r2.setPrimaryKeyName("o_orderkey");
        r2.isRoot = false;
        r2.isLeaf = false;
        r2.setParentRelation(r1);
        r2.setChildRelations(new ArrayList<>());
        r2.childRelations.add(r3);
        r2.setChildRelationsNum(0);
        r2.setTuplesIndex(new Hashtable<>());
        r2.setLiveTuplesIndex(new Hashtable<>());
        r2.setNonliveTuplesIndex(new Hashtable<>());
        r2.setS_counter(new Hashtable<>());

        r3.setRelationName("customer");
        r3.setPrimaryKeyName("c_custkey");
        r3.isRoot = false;
        r3.isLeaf = true;
        r3.setParentRelation(r2);
        r3.setChildRelations(null);
        r3.setChildRelationsNum(0);
        r3.setTuplesIndex(new Hashtable<>());
        r3.setLiveTuplesIndex(new Hashtable<>());
        r3.setNonliveTuplesIndex(new Hashtable<>());
        r3.setS_counter(new Hashtable<>());

        // set index of R and Rc
        IndexOfRelationAndChildRelation i1_2 = new IndexOfRelationAndChildRelation(r1, r2);
        IndexOfRelationAndChildRelation i2_3 = new IndexOfRelationAndChildRelation(r2, r3);
        r1.setIndexOfRandRc(new ArrayList<>());
        r1.indexOfRandRc.add(i1_2);
        r2.setIndexOfRandRc(new ArrayList<>());
        r2.indexOfRandRc.add(i2_3);
        r3.setIndexOfRandRc(null);

        relationMapPool.put("lineitem", r1);
        relationMapPool.put("orders", r2);
        relationMapPool.put("customer", r3);
    }

    private void initQuery4RelationPool() {
        relationMapPool.clear();
        RelationUnit r1 = new RelationUnit();
        RelationUnit r2 = new RelationUnit();

        r1.setRelationName("lineitem");
        r1.setPrimaryKeyName("l_orderkey&l_linenumber");
        r1.isRoot = true;
        r1.isLeaf = false;
        r1.setParentRelation(null);
        r1.setChildRelations(new ArrayList<>());
        r1.childRelations.add(r2);
        r1.setChildRelationsNum(1);
        r1.setTuplesIndex(new Hashtable<>());
        r1.setLiveTuplesIndex(new Hashtable<>());
        r1.setNonliveTuplesIndex(new Hashtable<>());
        r1.setS_counter(new Hashtable<>());

        r2.setRelationName("orders");
        r2.setPrimaryKeyName("o_orderkey");
        r2.isRoot = false;
        r2.isLeaf = true;
        r2.setParentRelation(r1);
        r2.setChildRelations(null);
        r2.setChildRelationsNum(0);
        r2.setTuplesIndex(new Hashtable<>());
        r2.setLiveTuplesIndex(new Hashtable<>());
        r2.setNonliveTuplesIndex(new Hashtable<>());
        r2.setS_counter(new Hashtable<>());


        // set index of R and Rc
        IndexOfRelationAndChildRelation i1_2 = new IndexOfRelationAndChildRelation(r1, r2);
        r1.setIndexOfRandRc(new ArrayList<>());
        r1.indexOfRandRc.add(i1_2);
        r2.setIndexOfRandRc(null);

        relationMapPool.put("lineitem", r1);
        relationMapPool.put("orders", r2);
    }


}
