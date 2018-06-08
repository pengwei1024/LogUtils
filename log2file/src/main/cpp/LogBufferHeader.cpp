//
// Created by pqpo on 2018/2/10.
//

#include <ctime>
#include "LogBufferHeader.h"

using namespace log_header;

//struct Header {
//    char magic;
//    size_t log_len;
//    size_t log_path_len;
//    char* log_path;
//    char isCompress;
//};

LogBufferHeader::LogBufferHeader(void *data, size_t size) : data_ptr((char *) data), data_size(size) {
}

LogBufferHeader::~LogBufferHeader() {
}

void *LogBufferHeader::originPtr() {
    return data_ptr;
}

Header* LogBufferHeader::getHeader() {
    Header* header = new Header();
    if (isAvailable()) {
        header->magic = kMagicHeader;
        size_t log_len = 0;
        memcpy(&log_len, data_ptr + sizeof(char), sizeof(size_t));
        header->log_len = log_len;
        size_t log_path_len = 0;
        memcpy(&log_path_len, data_ptr + sizeof(char) + sizeof(size_t), sizeof(size_t));
        header->log_path_len = log_path_len;
        char *log_path = new char[log_path_len + 1];
        memset(log_path, 0, log_path_len + 1);
        memcpy(log_path, data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t), log_path_len);
        header->log_path = log_path;
        char isCompress = (data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t) + log_path_len)[0];
        header->isCompress = isCompress == 1;
    }
    return header;
}

size_t LogBufferHeader::getHeaderLen() {
    if (isAvailable()) {
        size_t log_path_len = 0;
        memcpy(&log_path_len, data_ptr + sizeof(char) + sizeof(size_t), sizeof(size_t));
        return calculateHeaderLen(log_path_len);
    }
    return 0;
}

void *LogBufferHeader::ptr() {
    return data_ptr + getHeaderLen();
}

void *LogBufferHeader::write_ptr() {
    return data_ptr + getHeaderLen() + getLogLen();
}

void LogBufferHeader::initHeader(Header &header) {
    if ((sizeof(char) + sizeof(size_t) + sizeof(size_t) + header.log_path_len) > data_size) {
        return;
    }
    memcpy(data_ptr, &header.magic, sizeof(char));
    memcpy(data_ptr + sizeof(char), &header.log_len, sizeof(size_t));
    memcpy(data_ptr + sizeof(char) + sizeof(size_t), &header.log_path_len, sizeof(size_t));
    memcpy(data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t), header.log_path, header.log_path_len);
    char isCompress = 0;
    if (header.isCompress) {
        isCompress = 1;
    }
    memcpy(data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t) + header.log_path_len, &isCompress,
           sizeof(char));

}

size_t LogBufferHeader::getLogLen() {
    if (isAvailable()) {
        size_t log_len = 0;
        memcpy(&log_len, data_ptr + sizeof(char), sizeof(size_t));
        return log_len;
    }
    return 0;
}

size_t LogBufferHeader::getLogPathLen() {
    if (isAvailable()) {
        size_t log_path_len = 0;
        memcpy(&log_path_len, data_ptr + sizeof(char) + sizeof(size_t), sizeof(size_t));
        return log_path_len;
    }
    return 0;
}

char *LogBufferHeader::getLogPath() {
    if (isAvailable()) {
        size_t log_path_len = getLogPathLen();
        if (log_path_len > 0) {
            char *log_path = new char[log_path_len + 1];
            memset(log_path, 0, log_path_len + 1);
            memcpy(log_path, data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t), log_path_len);
            return log_path;
        }
    }
    return nullptr;
}

void LogBufferHeader::setLogLen(size_t log_len) {
    if (isAvailable()) {
        memcpy(data_ptr + sizeof(char), &log_len, sizeof(size_t));
    }
}

bool LogBufferHeader::isAvailable() {
    return data_ptr[0] == kMagicHeader;
}

bool LogBufferHeader::getIsCompress() {
    if (isAvailable()) {
        char isCompress = (data_ptr + sizeof(char) + sizeof(size_t) + sizeof(size_t) + getLogPathLen())[0];
        return isCompress == 1;
    }
    return false;
}

size_t LogBufferHeader::calculateHeaderLen(size_t log_path_len) {
    return sizeof(char) + sizeof(size_t) + sizeof(size_t) + log_path_len + sizeof(char);
}






