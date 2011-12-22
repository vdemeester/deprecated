# Description

Install gitolite from package (if available) *or* from source and sets up a few
configuration

# Requirements

## Cookbooks

* git

## Platform

Currently Debian and derived (Ubuntu, …) are supported. This might work on other
linux distribution (fedora, redhat, centos, …) but no been tested.

# Attributes

* gitolite[:use_backport] - on debian, use backport version instead of the default
release version ; this is currently tested only with squeeze and squeeze-backports.

# Usage

## 'default' recipe

Installs a gitolite server using the system package.

# TODO

* [0.2.0] : more configuration options (keydir, default config, …)
* [0.3.0] : install from source
