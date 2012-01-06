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
* gitolite[:username] - when using source installation, specify the user that will
hold the git repository (`git clone <username>@<host>:…`)
* gitolite[:admin][:username] - when using source installation, specify the name of
the admin (=> naming the admin ssh public key).
* gitolite[:admin][:key] - required when using source installation, specify the
content of the ssh public key of the admin.

# Usage

## 'default' recipe

Installs a gitolite server using the system package.

# TODO

* more configuration options (keydir, default config, …)
  * hook files, …
