/*
 * Copyright © 2017 Hl Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.hl.hweventsource.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import com.hl.hweventsource.cli.api.HweventsourceCliCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HweventsourceCliCommandsImpl implements HweventsourceCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(HweventsourceCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public HweventsourceCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("HweventsourceCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}
