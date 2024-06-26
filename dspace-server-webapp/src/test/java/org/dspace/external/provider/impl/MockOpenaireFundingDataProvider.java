/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.external.provider.impl;

import static org.mockito.Mockito.when;

import java.io.IOException;

import eu.openaire.jaxb.helper.OpenAIREHandler;
import eu.openaire.jaxb.model.Response;
import org.dspace.external.OpenaireRestConnector;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Mock the Openaire external source using a mock rest connector so that query
 * will be resolved against static test files
 * 
 */
public class MockOpenaireFundingDataProvider extends OpenaireFundingDataProvider {
    @Override
    public void init() throws IOException {
        OpenaireRestConnector restConnector = Mockito.mock(OpenaireRestConnector.class);

        when(restConnector.searchProjectByKeywords(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.startsWith("mushroom"))).thenAnswer(new Answer<Response>() {
                    public Response answer(InvocationOnMock invocation) {
                        try {
                            return OpenAIREHandler
                                    .unmarshal(this.getClass().getResourceAsStream("openaire-projects.xml"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });

        when(restConnector.searchProjectByKeywords(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                AdditionalMatchers.not(ArgumentMatchers.startsWith("mushroom")))).thenAnswer(new Answer<Response>() {
                    public Response answer(InvocationOnMock invocation) {
                        try {
                            return OpenAIREHandler
                                    .unmarshal(this.getClass().getResourceAsStream("openaire-no-projects.xml"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });

        when(restConnector.searchProjectByIDAndFunder(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenAnswer(new Answer<Response>() {
                    public Response answer(InvocationOnMock invocation) {
                        try {
                            return OpenAIREHandler
                                    .unmarshal(this.getClass().getResourceAsStream("openaire-project.xml"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });

        setConnector(restConnector);
    }
}
