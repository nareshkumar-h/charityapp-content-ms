package com.revature.charityappcontentms.service;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.charityappcontentms.dto.VideoContentDTO;
import com.revature.charityappcontentms.exception.ServiceException;
import com.revature.charityappcontentms.model.VideoContent;
import com.revature.charityappcontentms.repository.VideoContentRepository;

@Service
public class VideoContentService {
	@Autowired
	VideoContentRepository VideoContentRepositoryObj;
	@Transactional
    public void addContent(VideoContentDTO videoContent) throws ServiceException {
		
		VideoContent video = new VideoContent();
		video.setTitle(videoContent.getTitle());
		video.setUrl(videoContent.getUrl());
		video.setActive(true);
    	try{
    		
    		VideoContentRepositoryObj.save(video);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
        
            throw new ServiceException("UNABLE TO ADD CONTENT");
        }
        
	}
	 public List<VideoContent> contentList() throws ServiceException {
	        List<VideoContent> videoContent = null;
	        videoContent = VideoContentRepositoryObj.findAll();
	        if (  videoContent == null) {
	            throw new ServiceException("UNABLE TO LIST DONOR DETAILS");
	        }
	        return   videoContent;
	    }
}
