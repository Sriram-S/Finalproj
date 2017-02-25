/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.HP.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Joker;
import javax.inject.Named;

import sun.rmi.runtime.Log;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.HP.example.com",
                ownerName = "backend.myapplication.HP.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        Joker joker=new Joker();
        String s=joker.getJokes();
        MyBean response = new MyBean();
        response.setData(s);
        return response;
    }
 /*   @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {


        Joker joker=new Joker();
        String s=joker.getJokes();

        MyBean response = new MyBean();
        response.setData(s);

        return response;
    }*/

}
