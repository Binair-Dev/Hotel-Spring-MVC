package be.bnair.lovehotel.service;

import be.bnair.lovehotel.models.dto.ChamberDTO;
import be.bnair.lovehotel.models.forms.ChamberForm;

import java.util.List;

public interface ChamberService {

    void create(ChamberForm form);
    ChamberDTO getOne(Long id);
    void update(ChamberForm form, Long id);
    List<ChamberDTO>getAll();
}
