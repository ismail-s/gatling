---
title: "Scaling Out"
description: "Generating heavy load from multiple injectors"
lead: "Generating heavy load from multiple injectors"
date: 2021-04-20T18:30:56+02:00
lastmod: 2021-04-20T18:30:56+02:00
weight: 3040000
---

Sometimes, generating some very heavy load from a single machine might lead to saturating the OS or the network interface controller.

In this case, you might want to use several Gatling instances hosted using multiple machines.

## Scaling out easily with Gatling Enterprise

The clustering mode is a built-in feature of [Gatling Enterprise](https://gatling.io/enterprise/), our enterprise version. It will run your tests on multiple load injectors, aggregate your results live and close the instances after the tests.

These injectors can be deployed on an on-premises instances, or on the cloud: Amazon Web Services, Google Cloud Platform, Microsoft Azure, Digital Ocean, ...

{{< img src="pools.png" alt="Gatling Enterprise Injectors configuration" >}}

## Scaling out with Gatling open-source

Gatling open-source doesn't have a cluster mode, but you can achieve similar results manually. You will have to configure all your load injectors, and aggregate the results manually. The steps are:

* deploy Gatling on several machines along with the Simulation classes and the associated resources (data, bodies, etc...)
* launch them remotely from a script, with the `-nr` (no reports) option
* retrieve all the simulation.log files
* rename them so they don't clash
* place them into a folder in the results folder of a Gatling instance
* generate the reports with Gatling with the `-ro name-of-the-simulation-folder` (reports only), Gatling will pick all the files that match `.*\.log`
