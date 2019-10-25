package com.revature.charityappcontentms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.charityappcontentms.dto.Message;
import com.revature.charityappcontentms.dto.VideoContentDTO;
import com.revature.charityappcontentms.exception.ServiceException;

import com.revature.charityappcontentms.service.VideoContentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public class VideoContentController {

	@Autowired
	VideoContentService videoContentService;

	@GetMapping("/VideoContent")
	@ApiOperation(value = "  Add video content API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = VideoContentDTO.class),
            @ApiResponse(code = 400, message = "Invalid Credentials", response = Message.class) })
	public ResponseEntity<?>addContent(@RequestParam("title")String title,@RequestParam("url") String url)
	
	  {
	
		String errorMessage = null;
		VideoContentDTO videoContent=null;
		try {
			VideoContentDTO video= new VideoContentDTO();
			video.setTitle(title);
			video.setUrl(url);

			videoContentService.addContent(video);
			 return new ResponseEntity<>( HttpStatus.OK);
		}
			
			 catch (ServiceException e) {
		        	
		        	e.printStackTrace();

		            errorMessage = e.getMessage();
		           Message message = new Message(errorMessage);
		            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		        }
	}
	
}
