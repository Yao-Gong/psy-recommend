package persistence;

import domain.Psychology;
/**@author Richard **/
public interface PsychologyDAO {

	/**@return Psychology object matching the uid from database **/
	public Psychology getPsychology(String uid);
	/**@param psychology : legal Psychology object**/
	public void insertPsychology(Psychology psychology);
	/**@param psychology : legal Psychology object**/
	public void updatePsychology(Psychology psychology);
	/**@param psychology : legal Psychology object**/
	public void deletePsychology(Psychology psychology);
}
