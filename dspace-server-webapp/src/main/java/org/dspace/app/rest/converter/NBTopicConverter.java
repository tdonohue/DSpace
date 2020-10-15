/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.converter;

import org.dspace.app.nbevent.service.dto.NBTopic;
import org.dspace.app.rest.model.NBTopicRest;
import org.dspace.app.rest.projection.Projection;
import org.springframework.stereotype.Component;

@Component
public class NBTopicConverter implements DSpaceConverter<NBTopic, NBTopicRest> {


	@Override
	public Class<NBTopic> getModelClass() {
		return NBTopic.class;
	}

	@Override
	public NBTopicRest convert(NBTopic modelObject, Projection projection) {
		NBTopicRest rest = new NBTopicRest();
		rest.setId(modelObject.getId());
		rest.setLastEvent(modelObject.getLastEvent());
		rest.setName(modelObject.getName());
		rest.setProjection(projection);
		rest.setTotalSuggestions(modelObject.getTotalSuggestion());
		return rest;
	}

}
