package de.tekup.ex.Service;

import java.util.List;

import de.tekup.ex.DTO.MetRequest;
import de.tekup.ex.DTO.MetResponse;
import de.tekup.ex.Models.Met;


public interface MetService {
	List<Met> getAllMets();
    Met getMetByName(String nom);
    MetResponse createMet(MetRequest met);
    MetResponse modifyMet(String nom,MetRequest newMet);
    MetResponse deleteMetByName(String nom);

}
