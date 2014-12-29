FROM vdemeester/gitolite-base
MAINTAINER Vincent Demeester <vincent@sbr.pm>

RUN apt-get -y install git-annex

ENTRYPOINT ["/init", "/usr/sbin/sshd", "-D"]

EXPOSE 22
