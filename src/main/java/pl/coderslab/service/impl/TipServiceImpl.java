package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.FileInfo;
import pl.coderslab.model.Tag;
import pl.coderslab.model.Tip;
import pl.coderslab.model.dto.TipDto;
import pl.coderslab.repository.FileInfoRepository;
import pl.coderslab.repository.TipRepository;
import pl.coderslab.service.TagService;
import pl.coderslab.service.TipService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TipServiceImpl implements TipService {

    @Autowired
    TipRepository tipRepository;

    @Autowired
    TagService tagService;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Override
    public List<TipDto> findAll() {
        return tipRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TipDto addTip(TipDto tipDto) {
        Tip tip = fromDto(tipDto);
        tip = tipRepository.save(tip);
        return toDto(tip);
    }

    @Override
    public void deleteTip(Long tipId) {
        tipRepository.delete(tipId);
    }

    @Override
    public TipDto editTip(TipDto tipDto) {
        Tip tip = fromDto(tipDto);
        tip = tipRepository.save(tip);
        return toDto(tip);
    }

    private Tip fromDto(TipDto tipDto) {
        Tip tip;
        if(tipDto.getId() != null) {
            tip = tipRepository.findOne(tipDto.getId());
        } else {
            tip = new Tip();
        }
        tip.setDate(tipDto.getDate());
        tip.setTitle(tipDto.getTitle());
        tip.setDescription(tipDto.getDescription());
        tip.setNumberOfRecommend(tipDto.getNumberOfRecommend());
        List<Tag> tags = tipDto.getTags().stream().map(tagService::getTagByName).collect(Collectors.toList());
        tip.setTag(tags);
        tip.setVideoUrl(tipDto.getVideoUrl());
        if(tipDto.getFileName() != null){
            FileInfo fileInfo = fileInfoRepository.findByFileName(tipDto.getFileName());
            tip.setFileInfo(fileInfo);
        }
        return tip;
    }

    private TipDto toDto(Tip tip) {
        TipDto tipDto = new TipDto();
        tipDto.setId(tip.getId());
        tipDto.setDate(tip.getDate());
        tipDto.setTitle(tip.getTitle());
        tipDto.setDescription(tip.getDescription());
        tipDto.setNumberOfRecommend(tip.getNumberOfRecommend());
        List<String> tags = tip.getTag().stream().filter(Objects::nonNull).map(Tag::getName).collect(Collectors.toList());
        tipDto.setTags(tags);
        tipDto.setVideoUrl(tip.getVideoUrl());
        if(tip.getFileInfo() != null) {
            tipDto.setFileName(tip.getFileInfo().getFileName());
        }
        return tipDto;
    }
}
