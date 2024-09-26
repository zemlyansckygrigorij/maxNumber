package org.example.maxnumber.services;

import org.example.maxnumber.web.request.RequestInfo;
import org.springframework.stereotype.Component;


public interface FileComponent {
   int getMax(RequestInfo requestInfo);
}
