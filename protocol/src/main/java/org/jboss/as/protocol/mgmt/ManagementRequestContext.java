/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.protocol.mgmt;

import org.jboss.remoting3.Channel;
import org.jboss.remoting3.MessageOutputStream;

import java.io.IOException;

/**
 * The context for handling request.
 *
 * @author Emanuel Muckenhuber
 */
public interface ManagementRequestContext<A> {

    /**
     * Get the current batch id for this operation.
     *
     * @return the batch id
     */
    Integer getOperationId();

    /**
     * Get the attachment.
     *
     * @return the attachment
     */
    A getAttachment();

    /**
     * Get the underlying channel.
     *
     * @return the channel
     */
    Channel getChannel();

    /**
     * Get the protocol header.
     *
     * @return the protocol header
     */
    ManagementProtocolHeader getRequestHeader();

    /**
     * Execute async.
     *
     * @param task the task
     */
    void executeAsync(final AsyncTask<A> task);

    /**
     * Write a new message.
     *
     * @param header the protocol header
     * @return the message output stream
     */
    FlushableDataOutput writeMessage(final ManagementProtocolHeader header) throws IOException;

    interface AsyncTask<A> {

        /**
         * Execute the task.
         *
         * @throws Exception
         */
        void execute(final ManagementRequestContext<A> context) throws Exception;

    }
}