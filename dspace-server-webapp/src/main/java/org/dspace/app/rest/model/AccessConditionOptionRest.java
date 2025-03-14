/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Access Condition (ResourcePolicy) REST Resource
 *
 * @author Luigi Andrea Pascarelli (luigiandrea.pascarelli at 4science.it)
 */
public class AccessConditionOptionRest {

    private String name;

    @JsonInclude(Include.NON_NULL)
    private UUID groupUUID;

    @JsonInclude(Include.NON_NULL)
    private UUID selectGroupUUID;

    private Boolean hasStartDate;

    private Boolean hasEndDate;

    @JsonInclude(Include.NON_NULL)
    private LocalDate maxStartDate;

    @JsonInclude(Include.NON_NULL)
    private LocalDate maxEndDate;

    public LocalDate getMaxEndDate() {
        return maxEndDate;
    }

    public void setMaxEndDate(LocalDate maxEndDate) {
        this.maxEndDate = maxEndDate;
    }

    public Boolean getHasStartDate() {
        return hasStartDate;
    }

    public void setHasStartDate(Boolean hasStartDate) {
        this.hasStartDate = hasStartDate;
    }

    public Boolean getHasEndDate() {
        return hasEndDate;
    }

    public void setHasEndDate(Boolean hasEndDate) {
        this.hasEndDate = hasEndDate;
    }

    public LocalDate getMaxStartDate() {
        return maxStartDate;
    }

    public void setMaxStartDate(LocalDate maxStartDate) {
        this.maxStartDate = maxStartDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
