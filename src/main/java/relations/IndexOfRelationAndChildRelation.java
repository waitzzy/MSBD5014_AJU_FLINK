package relations;
import java.util.Hashtable;
import java.util.Map;

public class IndexOfRelationAndChildRelation {
    public RelationUnit baseRelation;
    public RelationUnit childRelation;
    public Map<Long, Long> indexRAndRc;

    public IndexOfRelationAndChildRelation(RelationUnit baseRelation, RelationUnit childRelation) {
        this.baseRelation = baseRelation;
        this.childRelation = childRelation;
        this.indexRAndRc = new Hashtable<>();
    }

    public RelationUnit getBaseRelation() {
        return baseRelation;
    }

    public void setBaseRelation(RelationUnit baseRelation) {
        this.baseRelation = baseRelation;
    }

    public RelationUnit getChildRelation() {
        return childRelation;
    }

    public void setChildRelation(RelationUnit childRelation) {
        this.childRelation = childRelation;
    }
}
