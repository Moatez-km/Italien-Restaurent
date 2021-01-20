package de.tekup.ex.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.ex.DTO.MetRequest;
import de.tekup.ex.DTO.MetResponse;
import de.tekup.ex.Models.Met;
import de.tekup.ex.Repositories.MetRepository;
@Service
public class MetServiceImpl implements MetService{
	private MetRepository metRepo;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public MetServiceImpl(MetRepository metRepo) {
        super();
        this.metRepo = metRepo;
    }

	@Override
	public List<Met> getAllMets() {
		// TODO Auto-generated method stub
	      return metRepo.findAll();
	}

	@Override
	public Met getMetByName(String nom) {
		// TODO Auto-generated method stub
		Optional<Met> opt = metRepo.findById(nom);
        Met met;
        if (opt.isPresent())
        {
            met = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas un met de ce nom");
        }
        return met;
	}

	@Override
	public MetResponse createMet(MetRequest met) {
		// TODO Auto-generated method stub
		 Met metRequest = mapper.map(met, Met.class);
	        metRepo.save(metRequest);
	        return mapper.map(metRequest, MetResponse.class);
	}

	@Override
	public MetResponse modifyMet(String nom, MetRequest newMet) {
		// TODO Auto-generated method stub
		 Met metRequest = mapper.map(newMet, Met.class);
	        Met thisMet = this.getMetByName(nom);
	        if (metRequest.getPrix()>0)
	        {
	            thisMet.setPrix(metRequest.getPrix());
	        }
	        if (metRequest.getType()!=null)
	        {
	            thisMet.setType(metRequest.getType());
	        }
	        metRepo.save(thisMet);
	        return mapper.map(thisMet, MetResponse.class);
	}

	@Override
	public MetResponse deleteMetByName(String nom) {
		// TODO Auto-generated method stub
		Met met = this.getMetByName(nom);
        metRepo.deleteById(nom);
        return mapper.map(met, MetResponse.class);
	}

}
