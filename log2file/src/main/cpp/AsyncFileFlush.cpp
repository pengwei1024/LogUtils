//
// Created by pqpo on 2017/11/23.
//

#include "AsyncFileFlush.h"


AsyncFileFlush::AsyncFileFlush() {
    async_thread = std::thread(&AsyncFileFlush::async_log_thread, this);
}

AsyncFileFlush::~AsyncFileFlush() {
    stopFlush();
}

void AsyncFileFlush::async_log_thread() {
    while (true) {
        std::unique_lock<std::mutex> lck_async_log_thread(async_mtx);
        while (!async_buffer.empty()) {
            FlushBuffer* data = async_buffer.back();
            async_buffer.pop_back();
            flush(data);
        }
        if (exit) {
            return;
        }
        async_condition.wait(lck_async_log_thread);
    }
}

ssize_t AsyncFileFlush::flush(FlushBuffer* flushBuffer) {
    ssize_t written = 0;
    char* log_file = flushBuffer->logFile();
    if(log_file != nullptr && flushBuffer->length() > 0) {

        FILE* _file_log = fopen(log_file, "ab+");
        if(_file_log != NULL) {
            written = fwrite(flushBuffer->ptr(), flushBuffer->length(), 1, _file_log);
            fflush(_file_log);
            fclose(_file_log);
        }
    }
    delete flushBuffer;
    return written;
}

bool AsyncFileFlush::async_flush(FlushBuffer* flushBuffer) {
    std::unique_lock<std::mutex> lck_async_flush(async_mtx);
    if (exit) {
        delete flushBuffer;
        return false;
    }
    async_buffer.push_back(flushBuffer);
    async_condition.notify_all();
    return true;
}

void AsyncFileFlush::stopFlush() {
    exit = true;
    async_condition.notify_all();
    async_thread.join();
}
