/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest;

import static org.dspace.app.rest.matcher.ItemAuthorityMatcher.matchItemAuthorityWithOtherInformations;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.dspace.app.rest.test.AbstractControllerIntegrationTest;
import org.junit.Test;

public class RorOrgUnitAuthorityIT extends AbstractControllerIntegrationTest {

    @Test
    public void testAuthority() throws Exception {

        Map<String, String> expectedExtras = new HashMap<>();
        expectedExtras.put("data-ror_orgunit_id", "https://ror.org/02z02cv32");
        expectedExtras.put("ror_orgunit_id", "https://ror.org/02z02cv32");
        expectedExtras.put("data-ror_orgunit_type", "Nonprofit");
        expectedExtras.put("ror_orgunit_type", "Nonprofit");
        expectedExtras.put("data-ror_orgunit_acronym", "WEICan, IEEC");
        expectedExtras.put("ror_orgunit_acronym", "WEICan, IEEC");
        expectedExtras.put("data-ror_orgunit_country", "CA");
        expectedExtras.put("data-ror_orgunit_countryName", "CA");
        expectedExtras.put("ror_orgunit_countryName", "CA");

        Map<String, String> expectedExtras2 = new HashMap<>();
        expectedExtras2.put("data-ror_orgunit_id", "https://ror.org/03vb2cr34");
        expectedExtras2.put("ror_orgunit_id", "https://ror.org/03vb2cr34");
        expectedExtras2.put("data-ror_orgunit_type", "Company");
        expectedExtras2.put("ror_orgunit_type", "Company");
        expectedExtras2.put("data-ror_orgunit_countryName", "Italia");
        expectedExtras2.put("ror_orgunit_countryName", "Italia");
        expectedExtras2.put("data-ror_orgunit_country", "IT");

        String token = getAuthToken(eperson.getEmail(), password);
        getClient(token).perform(get("/api/submission/vocabularies/OrgUnitAuthority/entries")
            .param("filter", "02z02cv32 OR 03vb2cr34"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._embedded.entries", hasSize(2)))
            .andExpect(jsonPath("$._embedded.entries",
                hasItems(
                    matchItemAuthorityWithOtherInformations("will be referenced::ROR-ID::https://ror.org/02z02cv32",
                            "Wind Energy Institute of Canada", "Wind Energy Institute of Canada", "vocabularyEntry",
                            expectedExtras),
                    matchItemAuthorityWithOtherInformations("will be referenced::ROR-ID::https://ror.org/03vb2cr34",
                            "4Science", "4Science", "vocabularyEntry",
                            expectedExtras2))));
    }

}
