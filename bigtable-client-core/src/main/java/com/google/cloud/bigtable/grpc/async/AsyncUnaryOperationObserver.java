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
package com.google.cloud.bigtable.grpc.async;

import io.grpc.stub.StreamObserver;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * A StreamObserver for unary async operations. It assumes that the operation is complete
 * as soon as a single response is received.
 * @param <T> The response type.
 */
public class AsyncUnaryOperationObserver<T> implements StreamObserver<T> {
  private final SettableFuture<T> completionFuture = SettableFuture.create();

  @Override
  public void onNext(T t) {
    completionFuture.set(t);
  }

  @Override
  public void onError(Throwable throwable) {
    completionFuture.setException(throwable);
  }

  @Override
  public void onCompleted() {
  }

  public ListenableFuture<T> getCompletionFuture() {
    return completionFuture;
  }
}