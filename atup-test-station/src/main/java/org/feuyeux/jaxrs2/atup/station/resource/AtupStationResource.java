package org.feuyeux.jaxrs2.atup.station.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("station")
public class AtupStationResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupStationResource.class.getName());
    private Integer status = AtupParam.DEVICE_IDLE;

    public AtupStationResource() {

    }

    @GET
    public Integer keepALive(@Context HttpServletRequest request) {
        //log.info("Request host=" + request.getRemoteHost());
        log.info("keep-a-live :: Request host =" + request.getRemoteAddr());
        return status;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer test(@Context HttpServletRequest request, AtupTestCase testCase) throws InterruptedException {
        status = AtupParam.DEVICE_RUNNING;
        //log.info("Request host=" + request.getRemoteHost());
        log.info("testing :: Request host =" + request.getRemoteAddr());
        log.info("testing :: testCase =" + testCase);
        Thread.sleep(30000);
        status = AtupParam.DEVICE_IDLE;
        return AtupParam.RESULT_OK;
    }
}