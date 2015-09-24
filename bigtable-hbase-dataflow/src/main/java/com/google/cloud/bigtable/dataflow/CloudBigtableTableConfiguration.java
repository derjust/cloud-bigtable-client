/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.dataflow;

import java.util.Collections;
import java.util.Map;

/**
 * This class defines information that a Cloud Bigtable client needs to connect to a user's Cloud
 * Bigtable cluster, including a table to connect to in the cluster.
 */
public class CloudBigtableTableConfiguration extends CloudBigtableConfiguration {

  private static final long serialVersionUID = 2435897354284600685L;

  /**
   * Converts a {@link CloudBigtableOptions} object to a {@link CloudBigtableTableConfiguration}.
   * @param options The {@link CloudBigtableOptions} object.
   * @return The new {@link CloudBigtableTableConfiguration}.
   */
  public static CloudBigtableTableConfiguration fromCBTOptions(CloudBigtableOptions options){
    return new CloudBigtableTableConfiguration(
        options.getBigtableProjectId(),
        options.getBigtableZoneId(),
        options.getBigtableClusterId(),
        options.getBigtableTableId(),
        Collections.<String, String> emptyMap());
  }

  /**
   * Builds a {@link CloudBigtableTableConfiguration}.
   */
  @SuppressWarnings("unchecked")
  public static class Builder<T extends Builder<?>> extends CloudBigtableConfiguration.Builder<T> {
    protected String tableId;

    /**
     * Specifies the table to connect to.
     * @param tableId The table to connect to.
     * @return The original {@link CloudBigtableTableConfiguration.Builder} for chaining
     * 		convenience.
     */
    public T withTableId(String tableId) {
      this.tableId = tableId;
      return (T) this;
    }

    /**
     * Builds the {@link CloudBigtableTableConfiguration}.
     * @return The new {@link CloudBigtableTableConfiguration}.
     */
    public CloudBigtableTableConfiguration build() {
      return new CloudBigtableTableConfiguration(projectId, zoneId, clusterId, tableId,
          additionalConfiguration);
    }
  }

  protected String tableId;

  // This is required for serialization of CloudBigtableScanConfiguration.
  CloudBigtableTableConfiguration() {
  }

  /**
   * Creates a {@link CloudBigtableTableConfiguration} using the specified information.
   * @param projectId The project ID for the cluster.
   * @param zoneId The zone where the cluster is located.
   * @param clusterId The cluster ID for the cluster.
   * @param tableId The table to connect to in the cluster.
   */
  public CloudBigtableTableConfiguration(String projectId, String zoneId, String clusterId,
      String tableId, Map<String, String> additionalConfiguration) {
    super(projectId, zoneId, clusterId, additionalConfiguration);
    this.tableId = tableId;
  }

  /**
   * Gets the table specified by the configuration.
   * @return The table ID.
   */
  public String getTableId() {
    return tableId;
  }

  /**
   * Creates a builder based on this configuration for further adoption
   * @return An initialized builder for this configuration
   */
  @Override
  public Builder<?> toBuilder() {
    Builder<?> builder = new Builder<Builder<?>>();

    toBuilder(builder);
    builder.tableId = this.tableId;

    return builder;
  }
}
