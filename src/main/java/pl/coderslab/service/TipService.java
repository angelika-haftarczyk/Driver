package pl.coderslab.service;

import pl.coderslab.model.Tip;
import pl.coderslab.model.dto.TipDto;

import java.util.List;

public interface TipService {
    List<TipDto> findAll();
    TipDto addTip(TipDto tipDto);
    void deleteTip(Long tipId);
    TipDto editTip(TipDto tipDto);

}
