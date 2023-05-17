package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.domain.Label;
import com.blog.exception.Code;
import com.blog.service.LabelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/label")
@ResponseBody
public class LabelController {
    private LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @RequestMapping("/getHotLabels")
    public DataResponse getHotLabels() {
        List<Label> hotLabels = labelService.getHotLabels();
        return DataResponse.success(Code.LABEL_GET_HOT, Code.LABEL_GET_HOT_MESSAGE, hotLabels);
    }
}
