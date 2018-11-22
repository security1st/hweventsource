/*
 * Copyright © 2017 Hl Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.hl.hweventsource;

import org.opendaylight.controller.md.sal.dom.api.DOMNotificationPublishService;
import org.opendaylight.controller.messagebus.spi.EventSourceRegistry;
import com.hl.hweventsource.sample.HelloWorldEventSourceManager;
import com.hl.hweventsource.sample.SampleEventSourceGenerator;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HweventsourceProvider {

    private static final Logger LOG = LoggerFactory.getLogger(HweventsourceProvider.class);

    private final DataBroker dataBroker;
    private final EventSourceRegistry eventSourceRegistry;
    private final DOMNotificationPublishService domPublish;
    private final Short numberSampleEventSources;
    private final Short messageGeneratePeriod;
    private final String messageText;

    private final HelloWorldEventSourceManager esm;

    public HweventsourceProvider(final DataBroker dataBroker,
                                 final EventSourceRegistry eventSourceRegistry,
                                 final DOMNotificationPublishService domPublish,
                                 final Short numberSampleEventSources,
                                 final Short messageGeneratePeriod,
                                 final String messageText) {

        this.dataBroker = dataBroker;

        // obtaining parameters of application from config subsystem
        //final HweventsourceBIProvider providerBI = new HweventsourceBIProvider();
        //final ProviderSession domCtx = getDomBrokerDependency().registerProvider(providerBI);

        // EventSourceRegistry is key service in event topic broker. All event sources have to be registered in this service
        this.eventSourceRegistry = eventSourceRegistry;

        this.domPublish = domPublish;
        //domCtx.getService(DOMNotificationPublishService.class);

        this.numberSampleEventSources = numberSampleEventSources;
        this.messageGeneratePeriod = messageGeneratePeriod;
        this.messageText = messageText;

        // Instance of HelloWorldEventSourceManager manage event sources, register and unregister them via EventSourceRegistry
        esm = new HelloWorldEventSourceManager(eventSourceRegistry);
        LOG.info("{}, {}, {}", numberSampleEventSources, messageGeneratePeriod, messageText);
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("HweventsourceProvider Session Initiated");


        // SampleEventSourceGenerator creates given number of event source and add them into HelloWorldEventSourceManager
        final SampleEventSourceGenerator seg = new SampleEventSourceGenerator(esm, domPublish);
        seg.generateEventSources(numberSampleEventSources, messageGeneratePeriod, messageText);
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() throws Exception{
        esm.close();
        LOG.info("HweventsourceProvider Closed");
    }
}