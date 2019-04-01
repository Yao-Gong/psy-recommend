package persistence;

import java.util.List;

import domain.Relation;
/**@author Richard **/
public interface RelationDAO {

	/**@return the all relations matching the uid from database **/
	public List<Relation> getRelation(String uid);
	/**@param relation : legal Relation object**/
	public void insertRelation(Relation relation);
	/**@param relation : legal Relation object**/
	public void deleteRelation(Relation relation);
	/**delete all relations of the user matching the uid**/
	public void deleteRelations(String uid);
}
