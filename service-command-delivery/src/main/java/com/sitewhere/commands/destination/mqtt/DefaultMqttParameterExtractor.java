/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.commands.destination.mqtt;

import java.util.List;

import com.sitewhere.commands.configuration.extractors.mqtt.DefaultMqttParameterExtractorConfiguration;
import com.sitewhere.commands.spi.ICommandDeliveryParameterExtractor;
import com.sitewhere.microservice.lifecycle.TenantEngineLifecycleComponent;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.IDeviceAssignment;
import com.sitewhere.spi.device.IDeviceNestingContext;
import com.sitewhere.spi.device.command.IDeviceCommandExecution;
import com.sitewhere.spi.microservice.lifecycle.LifecycleComponentType;

/**
 * Implements {@link ICommandDeliveryParameterExtractor} for
 * {@link MqttParameters}, allowing expressions to be defined such that the
 * tenant id and device token may be included in the topic name to target a
 * specific device.
 */
public class DefaultMqttParameterExtractor extends TenantEngineLifecycleComponent
	implements ICommandDeliveryParameterExtractor<MqttParameters> {

    /** Configuration */
    private DefaultMqttParameterExtractorConfiguration configuration;

    public DefaultMqttParameterExtractor(DefaultMqttParameterExtractorConfiguration configuration) {
	super(LifecycleComponentType.CommandParameterExtractor);
	this.configuration = configuration;
    }

    /*
     * @see com.sitewhere.commands.spi.ICommandDeliveryParameterExtractor#
     * extractDeliveryParameters(com.sitewhere.spi.device.IDeviceNestingContext,
     * java.util.List, com.sitewhere.spi.device.command.IDeviceCommandExecution)
     */
    @Override
    public MqttParameters extractDeliveryParameters(IDeviceNestingContext nesting,
	    List<? extends IDeviceAssignment> assignments, IDeviceCommandExecution execution)
	    throws SiteWhereException {
	MqttParameters params = new MqttParameters();
	params.setCommandTopic(getConfiguration().getCommandTopicExpression());
	params.setSystemTopic(getConfiguration().getSystemTopicExpression());
	return params;
    }

    protected DefaultMqttParameterExtractorConfiguration getConfiguration() {
	return configuration;
    }
}